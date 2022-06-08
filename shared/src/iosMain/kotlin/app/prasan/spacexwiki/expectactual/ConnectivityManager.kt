package app.prasan.spacexwiki.expectactual

import kotlinx.coroutines.flow.SharedFlow

internal actual class ConnectivityManager {
    actual fun isNetworkAvailable(): Boolean {
        return true
    }

    actual val networkConnectionStateFlow: SharedFlow<Boolean>
        get() = TODO("Not yet implemented")
}