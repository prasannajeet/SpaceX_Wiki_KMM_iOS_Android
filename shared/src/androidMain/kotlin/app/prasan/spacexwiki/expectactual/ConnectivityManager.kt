package app.prasan.spacexwiki.expectactual

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow

internal actual class ConnectivityManager(private val connectivityMgr: ConnectivityManager): ConnectivityManager.NetworkCallback() {

    private val _networkFlow =  MutableStateFlow(isNetworkAvailable())
    actual val networkConnectionStateFlow: SharedFlow<Boolean>
        get() = _networkFlow.asStateFlow()

    actual fun isNetworkAvailable(): Boolean {
        val activeNetwork = connectivityMgr.activeNetwork
        return activeNetwork?.let {
            val networkCapabilities = connectivityMgr.getNetworkCapabilities(it)
            networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } ?: false
    }

    init {
        val builder = NetworkRequest.Builder()
        val networkRequest = builder.build()
        connectivityMgr.registerNetworkCallback(networkRequest, this)
    }

    private fun stopListeningChanges() {
        connectivityMgr.unregisterNetworkCallback(this)
    }

    override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
        _networkFlow.value = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    override fun onLost(network: Network) {
        _networkFlow.value = false
    }
}