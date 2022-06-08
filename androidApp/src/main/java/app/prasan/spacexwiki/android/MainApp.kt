package app.prasan.spacexwiki.android


import android.content.Context
import android.widget.Toast
import androidx.multidex.MultiDexApplication
import app.prasan.spacexwiki.di.initKoin
import app.prasan.spacexwiki.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

class MainApp: MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        initKoin(
            module {
                single<Context> { this@MainApp }
                viewModelOf(::MainViewModel)
                single(named("doOnStartup")) {
                    { Toast.makeText(this@MainApp, "Welcome to SpaceX!", Toast.LENGTH_LONG).show() }
                }
                single(named("bundle_identifier")) { BuildConfig.APPLICATION_ID }
            }
        )
    }
}