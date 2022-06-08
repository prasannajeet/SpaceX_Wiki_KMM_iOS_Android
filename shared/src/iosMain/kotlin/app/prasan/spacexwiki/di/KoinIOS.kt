package app.prasan.spacexwiki.di

import app.prasan.spacexwiki.MainCallbackViewModel
import app.prasan.spacexwiki.expectactual.ConnectivityManager
import app.prasan.spacexwiki.expectactual.PlatformKtorClientEngine
import app.prasan.spacexwiki.usecase.GetSpaceXCompanyInfoUseCase
import co.touchlab.kermit.Logger
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import platform.Foundation.NSUserDefaults

fun initKoinIos(
    appInfo: String,
    doOnStartup: () -> Unit
): KoinApplication = initKoin(
    module {
        //single<Settings> { AppleSettings(userDefaults) }
        single(named("bundle_identifier")) { appInfo }
        single(named("doOnStartup")) { doOnStartup }
    }
)


actual val platformModule : Module = module {
    singleOf(::ConnectivityManager)
    singleOf(::PlatformKtorClientEngine)
    singleOf(::MainCallbackViewModel)
}

// Access from Swift to create a logger
@Suppress("unused")
fun Koin.loggerWithTag(tag: String) =
    get<Logger>(qualifier = null) { parametersOf(tag) }

// Access from Swift to get VM instance
@Suppress("unused") // Called from Swift
object KotlinDependencies : KoinComponent {
    fun getMainCallbackViewModel() = getKoin().get<MainCallbackViewModel>()
}
