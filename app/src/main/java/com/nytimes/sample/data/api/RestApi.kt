package com.nytimes.sample.data.api

import com.google.gson.*
import com.nytimes.sample.BuildConfig
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit
import com.google.gson.GsonBuilder
import retrofit2.Call


/**
 * Api invoke through this class. it use Retrofit and OKHttp as client.
 */
interface RestApi {

    @GET("/svc/mostpopular/v2/viewed/{period}.json")
    fun getNews(@Path("period") period: Int, @Query("api-key") key : String): Flowable<NewsResponse>

    @GET("/svc/mostpopular/v2/viewed/{period}.json")
    fun getNewsForTesting(@Path("period") period: Int, @Query("api-key") key : String): Call<NewsResponse>

    companion object {

        @get:Synchronized
        var adapter: Retrofit? = null
            private set

        val api: RestApi
            @Synchronized get() {
                if(adapter == null) {
                    initRestAdapter()
                }
                return adapter!!.create(RestApi::class.java)
            }


        @Synchronized
        private fun initRestAdapter() {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
            // use OkHttp client
            val httpClient = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor { chain ->
                    val original = chain.request()
                    val requestBuilder = original.newBuilder()
                        .header("Accept", "application/json")
                        .header("Content-Type", "application/x-www-form-urlencoded")
                    requestBuilder.method(original.method(), original.body())
                    val request = requestBuilder.build()
                    chain.proceed(request)
                }
                .addInterceptor(loggingInterceptor)
                .build()

            val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()

            adapter = Retrofit.Builder()
                .client(httpClient)
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory
                        .createWithScheduler(Schedulers.io())
                )
                .build()
        }


    }







}



