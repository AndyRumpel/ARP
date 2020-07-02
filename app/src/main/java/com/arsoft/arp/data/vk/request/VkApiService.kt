package com.arsoft.arp.data.vk.request

import com.arsoft.arp.data.vk.response.PlaylistResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface VkApiService {

    @GET(value = "audio.get")
    fun getPlaylist(
            @Query(value = "user_id")userId: Int,
            @Query(value = "access_token")accessToken: String,
            @Query(value = "v")version: String,
            @Query(value = "count")count: Int
    ): Deferred<PlaylistResponse>

}