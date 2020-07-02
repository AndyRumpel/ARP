package com.arsoft.arp.data.vk.request

import com.arsoft.arp.data.vk.response.PlaylistResponse
import com.arsoft.arp.helpers.API_VERSION
import com.arsoft.arp.helpers.Prefs
import com.arsoft.arp.helpers.TRACKS_COUNT
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class VkApiHelperImpl @Inject constructor(private val vkApiService: VkApiService, private val prefs: Prefs): VkApiHelper {
    override suspend fun getPlaylist(userId: Int): Deferred<PlaylistResponse> = vkApiService.getPlaylist(
            userId = userId,
            accessToken = prefs.accessToken,
            version = API_VERSION,
            count = TRACKS_COUNT
    )
}