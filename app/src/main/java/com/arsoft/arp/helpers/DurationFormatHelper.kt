package com.arsoft.arp.helpers

import java.text.SimpleDateFormat
import java.util.*

class DurationFormatHelper {
    companion object {

        fun durationFormat(duration: Int): String {
            val minutes = duration / 60
            val seconds = if (duration % 60 > 9) "${duration % 60}" else "0${duration % 60}"
            return "$minutes:$seconds"
        }
    }
}