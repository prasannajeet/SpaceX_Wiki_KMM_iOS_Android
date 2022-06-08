package app.prasan.spacexwiki.expectactual

import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*

actual class PlatformKtorClientEngine {

    actual fun factory(): HttpClientEngine {
        return OkHttp.create()
    }
}