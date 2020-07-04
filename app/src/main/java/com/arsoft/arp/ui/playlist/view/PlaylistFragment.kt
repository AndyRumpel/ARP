package com.arsoft.arp.ui.playlist.view

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arsoft.arp.R
import com.arsoft.arp.helpers.Prefs
import com.arsoft.arp.mvvm.playlist.states.PlaylistStates
import com.arsoft.arp.mvvm.playlist.viewmodel.PLaylistViewModel
import com.arsoft.arp.ui.playlist.adapter.PlaylistAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_playlist.*
import javax.inject.Inject

@AndroidEntryPoint
class PlaylistFragment : Fragment() {

    private val viewModel: PLaylistViewModel by viewModels()
    private lateinit var adapter: PlaylistAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager

    @Inject
    lateinit var prefs: Prefs

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_playlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPlaylist()

        adapter = PlaylistAdapter()
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        playlist_recycler.adapter = adapter
        playlist_recycler.layoutManager = layoutManager
        setupObservers(view = view)
    }

    private fun setupObservers(view: View) {
        viewModel.state.observe(viewLifecycleOwner, Observer {state ->
            when(state) {
                is PlaylistStates.LoadingState -> {
                    playlist_recycler.visibility = View.INVISIBLE
                    nothing_to_show.visibility = View.INVISIBLE
                    playlist_cpv.visibility = View.VISIBLE
                }
                is PlaylistStates.LoadingSucceededState -> {
                    playlist_recycler.visibility = View.VISIBLE
                    nothing_to_show.visibility = View.INVISIBLE
                    playlist_cpv.visibility = View.INVISIBLE
                    adapter.setupPlaylist(playlist = state.playlist)
                    adapter.notifyDataSetChanged()
                }
                is PlaylistStates.ErrorState<*> -> {
                    playlist_recycler.visibility = View.INVISIBLE
                    nothing_to_show.visibility = View.VISIBLE
                    playlist_cpv.visibility = View.INVISIBLE
                    when(state.message) {
                        is Int -> showMessage(state.message)
                        is String -> showMessage(state.message)
                    }
                }
                is PlaylistStates.DefaultState -> {
                    playlist_recycler.visibility = View.VISIBLE
                    nothing_to_show.visibility = View.INVISIBLE
                    playlist_cpv.visibility = View.INVISIBLE
                }
            }
        })
    }

    private fun <T: Any> showMessage(message: T) {
        when(message) {
            is Int -> {
                Toast.makeText(requireContext(), resources.getText(message), Toast.LENGTH_SHORT).show()
                Log.e("PLAYLIST", resources.getText(message).toString())
            }
            is String -> {
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                Log.e("PLAYLIST", message)
            }
            else -> {
                Toast.makeText(requireContext(), message.toString(), Toast.LENGTH_SHORT).show()
                Log.e("PLAYLIST", message.toString())
            }
        }
    }

}