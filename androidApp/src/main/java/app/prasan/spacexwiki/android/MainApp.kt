package app.prasan.spacexwiki.android


import android.content.Context
import androidx.multidex.MultiDexApplication
import app.prasan.companyinfo.di.featureCompanyInfoModule
import app.prasan.spacexwiki.di.initKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

class MainApp: MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        initKoin(
            module {
                includes(featureCompanyInfoModule)
                single<Context> { this@MainApp }
                single(named("doOnStartup")) {
                    { /*Toast.makeText(this@MainApp, "Welcome to SpaceX!", Toast.LENGTH_LONG).show()*/ }
                }
                single(named("bundle_identifier")) { BuildConfig.APPLICATION_ID }
            }
        )
    }
}