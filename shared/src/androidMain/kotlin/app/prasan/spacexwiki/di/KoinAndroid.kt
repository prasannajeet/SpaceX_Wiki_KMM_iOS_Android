package app.prasan.spacexwiki.di

import android.content.Context
import app.prasan.spacexwiki.expectactual.ConnectivityManager
import app.prasan.spacexwiki.expectactual.PlatformKtorClientEngine
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule: Module = module {

    single {
        get<Context>().getSystemService(Context.CONNECTIVITY_SERVICE) as android.net.ConnectivityManager
    }
    singleOf(::ConnectivityManager)
    singleOf(::PlatformKtorClientEngine)
}
