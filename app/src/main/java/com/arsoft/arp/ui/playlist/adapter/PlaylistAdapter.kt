package com.arsoft.arp.ui.playlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arsoft.arp.R
import com.arsoft.arp.data.vk.models.TrackModel
import com.arsoft.arp.helpers.DurationFormatHelper
import com.squareup.picasso.Picasso
import kotlin.collections.ArrayList

class PlaylistAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var playlist = ArrayList<TrackModel>()

    fun setupPlaylist(playlist: ArrayList<TrackModel>) {
        this.playlist = playlist
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cell_track_in_playlist, parent, false)
        return PlaylistViewHolder(itemView = itemView)
    }

    override fun getItemCount(): Int {
        return playlist.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PlaylistViewHolder).bind(playlist[position])
    }

    class PlaylistViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val artistTxt = itemView.findViewById<TextView>(R.id.artist)
        val titleTxt = itemView.findViewById<TextView>(R.id.title)
        val durationTxt = itemView.findViewById<TextView>(R.id.duration)
        val albumImage = itemView.findViewById<ImageView>(R.id.album_image)

        fun bind(model: TrackModel) {
            artistTxt.text = model.artist
            titleTxt.text = model.title
            durationTxt.text = DurationFormatHelper.durationFormat(model.duration)

            if (model.album != null) {
                Picasso.get()
                        .load(model.album.thumb.photo_68)
                        .into(albumImage)
            }
        }
    }
}