package com.target.targetcasestudy.utils

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest

object NetworkUtility {

    fun registerNetworkCallback(context: Context, networkCallbacks: ConnectivityManager.NetworkCallback){
        val connectivityManager=context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager?
        connectivityManager?.registerNetworkCallback(
            NetworkRequest.Builder().addCapability(
                NetworkCapabilities.NET_CAPABILITY_INTERNET).build(),networkCallbacks)
    }
}