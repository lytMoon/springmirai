package com.lytmoon.springmirai.apiservice;

import com.lytmoon.springmirai.bean.chatgpt.ChatReplyData;
import com.lytmoon.springmirai.bean.chatgpt.ChatRequest;
import com.lytmoon.springmirai.secret.ApiKey;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ChatApiService {

    @Headers({
            "Content-Type: application/json",
            "Authorization:Bear " + ApiKey.CHATGPT_APIKEY
    })
    @POST("v1/chat/completions")
    Call<ChatReplyData> getChatCompletion(@Body ChatRequest body);


}
