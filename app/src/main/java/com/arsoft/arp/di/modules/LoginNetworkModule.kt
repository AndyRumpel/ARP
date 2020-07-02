package com.arsoft.arp.di.modules

import com.arsoft.arp.data.login.request.LoginApiHelper
import com.arsoft.arp.data.login.request.LoginApiHelperImpl
import com.arsoft.arp.data.login.request.LoginApiService
import com.arsoft.arp.helpers.interceptors.UserAgentInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object LoginNetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson =
        GsonBuilder()
            .create()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(UserAgentInterceptor(userAgent = "VKAndroidApp/5.52-4543 (Android 5.1.1; SDK 22; x86_64; unknown Android SDK built for x86_64; en; 320x240)"))
            .build()

    @Provides
    @Singleton
    @Named("VK_OAUTH")
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://oauth.vk.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideLoginService(@Named("VK_OAUTH")retrofit: Retrofit): LoginApiService =
        retrofit.create(LoginApiService::class.java)

    @Provides
    @Singleton
    fun provideLoginApiHelper(loginApiHelper: LoginApiHelperImpl): LoginApiHelper = loginApiHelper
}