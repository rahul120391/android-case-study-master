package com.target.targetcasestudy.dataProvider.connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import com.target.targetcasestudy.utils.NetworkUtility
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NetworkConnectivityListenerImp @Inject constructor(context: Context): ConnectivityManager.NetworkCallback() {

    var isAvailable = false
    init {
        NetworkUtility.registerNetworkCallback(context,this)
    }
    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        isAvailable=true
    }

    override fun onUnavailable() {
        super.onUnavailable()
        isAvailable=false
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        isAvailable=false
    }

    override fun onLosing(network: Network, maxMsToLive: Int) {
        super.onLosing(network, maxMsToLive)
        isAvailable=false
    }
}