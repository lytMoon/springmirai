package com.lytmoon.springmirai.apiservice;

import com.lytmoon.springmirai.bean.deepseek.DeepSeekRequest;
import com.lytmoon.springmirai.bean.deepseek.DeepSeekResponse;
import com.lytmoon.springmirai.secret.ApiKey;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface DeepSeekApiService {

    @Headers({
            "Content-Type: application/json",
            "Authorization: Bearer " + ApiKey.DEEP_SEEK_APIKEY
    })
    @POST("chat/completions")
    Call<DeepSeekResponse> createCompletion(@Body DeepSeekRequest request);
}
