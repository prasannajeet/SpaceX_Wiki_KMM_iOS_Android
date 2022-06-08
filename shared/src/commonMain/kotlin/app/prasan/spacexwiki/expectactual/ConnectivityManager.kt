package app.prasan.spacexwiki.expectactual

import kotlinx.coroutines.flow.SharedFlow

internal expect class ConnectivityManager {
    fun isNetworkAvailable(): Boolean
    val networkConnectionStateFlow: SharedFlow<Boolean>
}