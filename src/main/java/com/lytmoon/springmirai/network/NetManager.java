package com.lytmoon.springmirai.network;

import com.lytmoon.springmirai.apiservice.ChatApiService;
import com.lytmoon.springmirai.apiservice.DeepSeekApiService;
import com.lytmoon.springmirai.bean.chat.GroupContent;
import com.lytmoon.springmirai.bean.chat.GroupContext;
import com.lytmoon.springmirai.bean.chatgpt.ChatReplyData;
import com.lytmoon.springmirai.bean.deepseek.DeepSeekRequest;
import com.lytmoon.springmirai.bean.deepseek.DeepSeekResponse;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NetManager {


    private static NetManager instance;
    public List<ChatReplyData> replyList = new ArrayList<>();
    public List<DeepSeekResponse> dsresponseList = new ArrayList<>();

    private static final String url = "https://oa.api2d.net/";
    static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build();

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static final ChatApiService chatApi = retrofit.create(ChatApiService.class);


    Retrofit dsretrofit = new Retrofit.Builder()
            .baseUrl("https://api.deepseek.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    // 私有构造函数以防止实例化
    private NetManager() {
    }

    // 提供公共静态方法来获取实例
    public static synchronized NetManager getInstance() {
        if (instance == null) {
            instance = new NetManager();
        }
        return instance;
    }

    HashMap<String, GroupContext> upDownMap = new HashMap<>();
    DeepSeekApiService dsService = dsretrofit.create(DeepSeekApiService.class);

    /**
     * model一共有两个分别是  deepseek-chat 跟  deepseek-reasoner   (R1模型)
     * 0是 v3
     * 1是r1
     */


    GroupContext getGroupContext(String groupIDStr) {
        if (!upDownMap.containsKey(groupIDStr)) {
            upDownMap.put(groupIDStr, new GroupContext(groupIDStr, new ArrayList<>()));
        }
        return upDownMap.get(groupIDStr);
    }


    public void getDeepSeekResponse(String ques, int modelCode, @NotNull GroupMessageEvent event) {
//        long groupID, String sender
        long groupID = event.getGroup().getId();
        String sender = event.getSender().getNameCard();

        String groupIDStr = Long.toString(groupID);
        //拿到每个群单独的上下文对象
        GroupContext groupContext = getGroupContext(groupIDStr);

        String model = "";
        if (modelCode == 0) model = "deepseek-chat";
        if (modelCode == 1) model = "deepseek-reasoner";

        String prevResultInfo = "下面是最高设定";
        String normalPrev = "设定前提：你的名字叫做卷卷，请你记住，你的主人是李伊侗,群里的昵称是帅气，当别人问你的主人是你再描述，没问的时候保持低调，在群里面请不要评价他人，注意不要评价他人";
        prevResultInfo += normalPrev;

        String anotherQues = "群里面最恶心的人是卢布，也就是墨子玩家，它的群id是各位的墨子大爹，一看就是最小丑的人,群里面有个玩的很菜的ad公孙离玩家pengee,主人是司马懿大神，群主刘邦很强，还有一位澜朋友，手法很超标" +
                "问到你群员的时候再评价群成员，没有的时候保持低调，不要什么问题后面都加上评价别人";
        if (groupID == 965016247) {
//            prevResultInfo += anotherQues;
            prevResultInfo += "主人的群id是善战的师";
        }

        String prevInfo = "如果用户的提问中有R1或者r1，这只是一个标志而不是问题，你思考的时候可以忽略掉这个标签，回答后面的内容就行" +
                "如果别人让你切换模型你说你默认是v3如果下次问问题前面加上r1或者R1标签就是r1模型，也就是深度思考模型，这是可以切换的，";
        prevResultInfo += prevInfo;


        //下面是用户问题相关
        String questionResult = "";
        questionResult += "我是群里面的" + sender;
        questionResult += "我的问题是" + ques.replaceAll("^@\\d+", "");

        //添加上下文
        StringBuilder upDownInfo = new StringBuilder("下面是之前对话的上下文，应该有很多条，你要结合最新的上下文来回答问题，下面的顺序是从以前到现在的，上下文中最新的回答在后面，注意顺义");
        groupContext.getContentList().forEach(content -> {
            upDownInfo.append("用户").append(content.getUserCardName()).append("的问题是").append(content.getUserQuestion()).append("\n卷卷回答：").append(content.getUserApply()).append("\n");
        });

        prevResultInfo += upDownInfo.toString();


        DeepSeekRequest.Message systemMessage = new DeepSeekRequest.Message("system", prevResultInfo);
        DeepSeekRequest.Message userMessage = new DeepSeekRequest.Message("user", questionResult);
        DeepSeekRequest request = new DeepSeekRequest(model, Arrays.asList(systemMessage, userMessage), false);

        Call<DeepSeekResponse> call = dsService.createCompletion(request);

        try {
            Response<DeepSeekResponse> response = call.execute();
            if (response.isSuccessful()) {
                dsresponseList.clear();
                dsresponseList.add(response.body());
                //添加到上下文缓存里面
                groupContext.getContentList().add(new GroupContent(sender, ques.replaceAll("^@\\d+", ""), dsresponseList.get(0).getChoices().get(0).getMessage().getContent()));

                System.out.println(dsresponseList.get(0).getChoices().get(0).getMessage().getContent());
            } else {
                System.out.println("Request failed: " + response.code());
                System.out.println("Request failed: " + response.errorBody().string());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
