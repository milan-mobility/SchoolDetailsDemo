package com.example.retrofit


import android.app.Application
import android.content.Context
import com.example.utils.BaseUrlPath
import com.example.utils.MyDataStore
import com.example.utils.RequestCodeCheck
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApiClient {


    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {

        val httpClient = HttpLoggingInterceptor()
        httpClient.setLevel(HttpLoggingInterceptor.Level.BODY)

        /* val okhttp = OkHttpClient.Builder()
             .connectTimeout(240, TimeUnit.SECONDS)
             .writeTimeout(240, TimeUnit.SECONDS)
             .readTimeout(240, TimeUnit.SECONDS)
             .build()
 */

        val okhttp = OkHttpClient.Builder()

        okhttp.addInterceptor { chain ->
            chain.proceed(chain.request().newBuilder().also {
                it.addHeader(
                    "Authorization",
                    "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiZTE1NmY2ZmNlZDQ2Y2I5MWI2ODVjNDE2MzdlZGJkZjk3MGI5NDJjZjc2YTM0NjliMzBlYzQzY2JmYjI5ZDVlMjcyNGNlZmRkZWQ2NmI2YTMiLCJpYXQiOjE2Nzc1NzEzODguNzc3NjM4LCJuYmYiOjE2Nzc1NzEzODguNzc3NjQ1LCJleHAiOjE3MDkxMDczODguNzU3NTc4LCJzdWIiOiI4NjgiLCJzY29wZXMiOltdfQ.Dgrdf54Lx22rewUo8Qx8ZRi2lsI-YX7g7SX7Tvc35MpJUMhR5nAFY41GCz9w8AY_pewwofaQVWKFxZZhxmMe21FvznQ2NG8_yLNjF5e5wY-WQqkeXml3U9cJrhQogAP2fs7W9J1Iefc0K6kfjp7JJCd6gMOmNHRZgF2tHGYoQoZswMoAU_xw2Furm_eWbLa48yqRdGc5Jq7I1sI5EF1LmdCsZUbx4nqBXScUR-C2E38cjhEZHzsBPsJpyR3QqrAifJtAA3jx87idayhePC2Pzp1IehGdJ7EF9NplEqULzl8hkgMx38xiCKlpqRmhYfIZimYTmW4tIM6DEzWOpnM6ArU-EZinqVV40oapdSgfy9OuzXoVXqJRcWtBwgHY0y4iQBHVpcWyFxhI2In2Km3WBl4RNi_wxUMEsapztJLY9N16PY0iQF6QRyiEgrXQzxukMnh2w9ujAQ7E7wkmPiRhEcQIag-sbHL4jn8mwIu8kNanSVcM2Z1lfOGKQiCVPxDfwB66KyP--N0Ebk-JExvaa2MSnmNDYPksIvDaZUN5Z_GpONq3UjFh6ELadS6TQIHh7U4OdbdowWpek7IR86Z54s2qSYB-41qJ6I8YP9hSHhUd8W3iLvXVq35mrnSVuXCua2wFBu11H4otRGRGgfhL4YwjkYphe7kxoWkRGSpze_M"
                )
            }.build())
        }

        return Retrofit.Builder()
            .baseUrl(BaseUrlPath.baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).client(okhttp.build()).build()
    }

    @Provides
    @Singleton
    fun provideApiInterface(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideRequestCodeCheck(): RequestCodeCheck {
        return RequestCodeCheck()
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideMyDataStore(context: Context): MyDataStore {
        return MyDataStore(context)
    }


}