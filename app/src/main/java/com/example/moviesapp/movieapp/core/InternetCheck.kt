package com.example.moviesapp.movieapp.core

import android.util.Log
import kotlinx.coroutines.coroutineScope
import java.net.InetSocketAddress
import java.net.Socket


object InternetCheck {

    suspend fun isNetworkAvailable() = coroutineScope {
        return@coroutineScope try {
            val sock = Socket()
            val socketAddress = InetSocketAddress("8.8.8.8", 53)
            Log.d("socket", "${socketAddress}")
            sock.connect(socketAddress, 1500)
            sock.close()
            true
        } catch (e: Exception){
            false
        }
    }


}