package com.arsoft.arp.di.modules

import com.arsoft.arp.data.login.request.LoginApiHelper
import com.arsoft.arp.data.login.request.LoginApiHelperImpl
import com.arsoft.arp.data.login.request.LoginApiService
import com.arsoft.arp.data.vk.request.VkApiHelper
import com.arsoft.arp.data.vk.request.VkApiHelperImpl
import com.arsoft.arp.data.vk.request.VkApiService
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object VkApiNetworkModule {

    @Provides
    @Singleton
    @Named("VK_API")
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .baseUrl("https://api.vk.com/method/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .client(okHttpClient)
                    .build()

    @Provides
    @Singleton
    fun provideVkApiService(@Named("VK_API")retrofit: Retrofit): VkApiService =
            retrofit.create(VkApiService::class.java)

    @Provides
    @Singleton
    fun provideVkApiHelper(vkApiHelper: VkApiHelperImpl): VkApiHelper = vkApiHelper
}