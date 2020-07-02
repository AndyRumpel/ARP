package com.arsoft.arp.data.vk.repository

import com.arsoft.arp.data.vk.models.TrackModel
import com.arsoft.arp.data.vk.request.VkApiHelper
import javax.inject.Inject

class VkRepository @Inject constructor(private val vkApiHelper: VkApiHelper){

    suspend fun getPlaylist(userId: Int): ArrayList<TrackModel> {
        val response = vkApiHelper.getPlaylist(userId = userId).await()
        val playlist = ArrayList<TrackModel>()
        for (item in response.response.items) {
            playlist.add(TrackModel(
                    access_key = item.access_key,
                    ads = item.ads,
                    album = item.album,
                    artist = item.artist,
                    date = item.date,
                    duration = item.duration,
                    featured_artists = item.featured_artists,
                    genre_id = item.genre_id,
                    id = item.id,
                    is_explicit = item.is_explicit,
                    is_focus_track = item.is_focus_track,
                    is_licensed = item.is_licensed,
                    main_artists = item.main_artists,
                    owner_id = item.owner_id,
                    short_videos_allowed = item.short_videos_allowed,
                    stories_allowed = item.stories_allowed,
                    subtitle = item.subtitle,
                    title = item.title,
                    track_code = item.track_code,
                    url = item.url
            ))
        }

        return playlist
    }
}