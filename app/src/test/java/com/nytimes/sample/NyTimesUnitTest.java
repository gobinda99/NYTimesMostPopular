package com.nytimes.sample;


import com.nytimes.sample.data.api.NewsResponse;
import com.nytimes.sample.data.api.RestApi;
import org.junit.Test;

import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;


public class NyTimesUnitTest {

    @Test
    public void urlMatch_check (){
        assertEquals("https://api.nytimes.com/", BuildConfig.API_URL);
    }


    @Test
    public void api_success () throws IOException{
        Call<NewsResponse> call = RestApi.Companion.getApi().getNewsForTesting(1,BuildConfig.API_KEY);
            Response<NewsResponse> response = call.execute();
            NewsResponse newsResponse = response.body();
            assertTrue(response.isSuccessful() && "OK".equals(newsResponse != null ? newsResponse.getStatus():null));
    }


    @Test
    public void api_unauthorized () throws IOException{
        Call<?> call = RestApi.Companion.getApi().getNewsForTesting(1,"");
        Response response = call.execute();
        assertTrue(!response.isSuccessful() && response.code() == 401);
    }



}
