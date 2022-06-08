package app.prasan.spacexwiki.expectactual

import io.ktor.client.engine.*

expect class PlatformKtorClientEngine {
    fun factory(): HttpClientEngine
}