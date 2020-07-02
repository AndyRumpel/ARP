package com.arsoft.arp.data.vk.request

import com.arsoft.arp.data.vk.response.PlaylistResponse
import kotlinx.coroutines.Deferred

interface VkApiHelper {
    suspend fun getPlaylist(userId: Int): Deferred<PlaylistResponse>
}