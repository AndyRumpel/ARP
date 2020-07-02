package com.arsoft.arp.data.vk.response

data class Album(
    val access_key: String,
    val id: Int,
    val owner_id: Int,
    val thumb: Thumb,
    val title: String
)