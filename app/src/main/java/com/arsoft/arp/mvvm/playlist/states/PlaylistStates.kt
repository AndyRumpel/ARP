package com.arsoft.arp.mvvm.playlist.states

import com.arsoft.arp.data.vk.models.TrackModel

sealed class PlaylistStates {

    class DefaultState: PlaylistStates()
    class ErrorState<T>(val message: T): PlaylistStates()
    class LoadingState: PlaylistStates()
    class LoadingSucceededState(val playlist: ArrayList<TrackModel>): PlaylistStates()

}