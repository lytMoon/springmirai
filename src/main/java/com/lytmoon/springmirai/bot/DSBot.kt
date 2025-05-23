package com.lytmoon.springmirai.bot

import com.lytmoon.springmirai.apiservice.DeepSeekApiService
import com.lytmoon.springmirai.bean.chat.ChatContext
import com.lytmoon.springmirai.bean.chat.GroupContent
import com.lytmoon.springmirai.bean.deepseek.DeepSeekRequest
import com.lytmoon.springmirai.bean.deepseek.DeepSeekResponse
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.mamoe.mirai.event.events.GroupMessageEvent
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * 托管代理 deep_seek 群机器人
 * by lytMoon
 */

object DSBot {

    private var contextMap = HashMap<String, ChatContext>()

    private val responseList: MutableList<DeepSeekResponse> = mutableListOf()


    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.deepseek.com/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val dsService: DeepSeekApiService = retrofit.create<DeepSeekApiService>(DeepSeekApiService::class.java)


    private fun loadInfo(event: GroupMessageEvent): String =
        StringBuilder().apply {
            append(loadPrevInfoStep(event))
            append(loadGroupMembers(event))
            append(loadChatContext(event.group.id.toString()))
        }.toString()


    private fun loadPrevInfoStep(event: GroupMessageEvent): String =
        StringBuilder().apply {
            append("你的名字叫做卷卷，你的主人是李伊侗。")
            append("当别人问你的主人是你再描述，没问的时候保持低调，在群里面请不要评价他人，注意不要评价他人。")
            append("")
            if (event.group.id == 965016247L) {
                append("主人的群id是全方位的高手")
            }
            append("如果用户的提问中有R1或者r1，这只是一个标志而不是问题，你思考的时候可以忽略掉这个标签，回答后面的内容就行。")
            append("如果别人让你切换模型你说你默认是v3如果下次问问题前面加上r1或者R1标签就是r1模型，也就是深度思考模型，这是可以切换的")
        }.toString()


    private fun loadGroupMembers(event: GroupMessageEvent): String =
        StringBuilder().apply {
            append("下面是群成员列表")
            for (member in event.group.members) {
                append(member.nameCard)
                append(" ")
            }
        }.toString()


    private fun loadChatContext(groupIDStr: String): String {

        return StringBuilder().apply {
            append("下面是之前对话的上下文，你要结合最新的上下文来回答问题,")
            append("下面的顺序是从以前到现在的，上下文中最新的回答在后面。")
            append("请注意先后顺序")
            getChatContext(groupIDStr).contentList.forEach { content: GroupContent ->
                append("用户")
                append(content.userCardName)
                append("的问题是")
                append(content.userQuestion)
                append("\n卷卷回答：")
                append(content.userApply).append("\n")
            }
        }.toString()
    }


    @OptIn(DelicateCoroutinesApi::class)
    fun sendRequest(ques: String, chatMode: ChatMode, event: GroupMessageEvent) {
        val chatContext: ChatContext = getChatContext(event.group.id.toString())
        val userQues = getUserQues(ques, event)
        val systemInfo = loadInfo(event)
        val model = chatMode.mode


        val systemMessage = DeepSeekRequest.Message("system", systemInfo)
        val userMessage = DeepSeekRequest.Message("user", userQues)
        val request = DeepSeekRequest(model, Arrays.asList<DeepSeekRequest.Message>(systemMessage, userMessage), false)

        val call: Call<DeepSeekResponse> = dsService.createCompletion(request)

        try {
            val response = call.execute()
            if (response.isSuccessful) {
                responseList.clear()
                responseList.add(response.body()!!)

                unloadChatContext(
                    chatContext,
                    GroupContent(
                        event.sender.nameCard,
                        ques.replace("^@\\d+".toRegex(), ""),
                        responseList[0].choices[0].message.content
                    )
                )

                GlobalScope.launch {
                    event.group.sendMessage(responseList[0].choices[0].message.content)
                }

                println(responseList.get(0).getChoices().get(0).getMessage().getContent())

            } else {
                println("Request failed: " + response.code())
                println("Request failed: " + response.errorBody()!!.string())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }


    private fun getUserQues(ques: String, event: GroupMessageEvent): String {
        var questionResult = ""
        questionResult += "我是群里面的${event.sender.nameCard}"
        questionResult += "我的问题是" + ques.replace("^@\\d+".toRegex(), "")
        return questionResult
    }

    private fun getChatContext(id: String): ChatContext {

        if (!contextMap.containsKey(id)) {
            contextMap[id] = ChatContext(id, mutableListOf())
        }

        return contextMap[id]!!
    }


    private fun unloadChatContext(context: ChatContext, groupContent: GroupContent) {
        if (context.contentList.size >= 50) {
            context.contentList.removeAt(0)
        }
        context.contentList.add(groupContent)

    }

}


