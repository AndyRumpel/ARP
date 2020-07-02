package com.arsoft.arp.data.vk.response

data class Item(
    val access_key: String,
    val ads: Ads,
    val album: Album,
    val artist: String,
    val date: Int,
    val duration: Int,
    val featured_artists: List<FeaturedArtist>,
    val genre_id: Int,
    val id: Int,
    val is_explicit: Boolean,
    val is_focus_track: Boolean,
    val is_licensed: Boolean,
    val main_artists: List<MainArtist>,
    val owner_id: Int,
    val short_videos_allowed: Boolean,
    val stories_allowed: Boolean,
    val subtitle: String,
    val title: String,
    val track_code: String,
    val url: String
)