package com.arsoft.arp.mvvm.playlist.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arsoft.arp.R
import com.arsoft.arp.data.vk.repository.VkRepository
import com.arsoft.arp.helpers.Prefs
import com.arsoft.arp.helpers.default
import com.arsoft.arp.helpers.set
import com.arsoft.arp.mvvm.playlist.states.PlaylistStates
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.Exception

class PLaylistViewModel @ViewModelInject constructor(
        private val vkRepository: VkRepository,
        private val prefs: Prefs
): ViewModel(){

    val state = MutableLiveData<PlaylistStates>().default(defaultValue = PlaylistStates.DefaultState())

    fun getPlaylist() {
        val userId = prefs.userId
        state.set(newValue = PlaylistStates.LoadingState())
        if (userId == 0) {
            state.set(newValue = PlaylistStates.ErrorState(R.string.empty_user_id))
        } else {
            CoroutineScope(Dispatchers.IO).async {
                try {
                    val playlist = vkRepository.getPlaylist(userId = userId)
                    if (playlist.isNotEmpty()) {
                        launch(Dispatchers.Main) {
                            state.set(newValue = PlaylistStates.LoadingSucceededState(playlist = playlist))
                        }
                    } else {
                        launch(Dispatchers.Main) {
                            state.set(newValue = PlaylistStates.ErrorState(R.string.playlist_loading_failed))
                        }
                    }
                } catch (e: Exception) {
                    launch(Dispatchers.Main) {
                        state.set(newValue = PlaylistStates.ErrorState(e.localizedMessage!!))
                    }
                }
            }
        }
    }
}