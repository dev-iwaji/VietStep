package com.example.vocabapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Network

class NetworkMonitor(
    context: Context
) {
    private val connectivityManager =
        context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

    fun isOnline(): Boolean {
        val network =
            connectivityManager.activeNetwork
                ?: return false

        val capabilities =
            connectivityManager
                .getNetworkCapabilities(network)
                ?: return false

        return capabilities.hasCapability(
            NetworkCapabilities.NET_CAPABILITY_INTERNET
        ) &&
                capabilities.hasCapability(
                    NetworkCapabilities.NET_CAPABILITY_VALIDATED
                )
    }

    fun registerCallback(
        onChanged: (Boolean) -> Unit
    ): ConnectivityManager.NetworkCallback {
        val callback =
            object : ConnectivityManager.NetworkCallback() {

                override fun onAvailable(
                    network: Network
                ) {
                    onChanged(isOnline())
                }

                override fun onLost(
                    network: Network
                ) {
                    onChanged(isOnline())
                }

                override fun onCapabilitiesChanged(
                    network: Network,
                    capabilities: NetworkCapabilities
                ) {
                    val online =
                        capabilities.hasCapability(
                            NetworkCapabilities.NET_CAPABILITY_INTERNET
                        ) &&
                                capabilities.hasCapability(
                                    NetworkCapabilities.NET_CAPABILITY_VALIDATED
                                )

                    onChanged(online)
                }
            }

        connectivityManager.registerDefaultNetworkCallback(
            callback
        )

        return callback
    }

    fun unregisterCallback(
        callback: ConnectivityManager.NetworkCallback
    ) {
        connectivityManager.unregisterNetworkCallback(
            callback
        )
    }
}
