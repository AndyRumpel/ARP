package com.arsoft.arp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.*
import androidx.navigation.fragment.findNavController
import com.arsoft.arp.R
import com.arsoft.arp.helpers.Prefs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import kotlin.concurrent.fixedRateTimer

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var prefs: Prefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = nav_host_fragment.findNavController()
        val inflater = navController.navInflater

        if (prefs.accessToken != "0" && prefs.userId != 0) {

            navController.navigate(R.id.playlistFragment)
            val graph = inflater.inflate(R.navigation.main_nav_graph)
            navController.graph = graph
        } else {
            navController.navigate(R.id.loginFragment)
        }

    }
}