package com.target.targetcasestudy.dataProvider.connectivity

import android.net.ConnectivityManager
import android.net.Network
import com.target.targetcasestudy.utils.Utility
import javax.inject.Inject

class NetworkConnectivityListenerImp @Inject constructor(): ConnectivityManager.NetworkCallback() {

    var isAvailable = false
    init {
        Utility.registerNetworkCallback(this)
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