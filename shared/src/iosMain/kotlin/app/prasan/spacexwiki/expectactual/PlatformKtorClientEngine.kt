package app.prasan.spacexwiki.expectactual

import io.ktor.client.engine.*
import io.ktor.client.engine.darwin.*

actual class PlatformKtorClientEngine {

    actual fun factory(): HttpClientEngine {
        return Darwin.create() {
            configureRequest {
                setAllowsCellularAccess(true)
            }
        }
    }
}