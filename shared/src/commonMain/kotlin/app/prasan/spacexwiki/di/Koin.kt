package app.prasan.spacexwiki.di


import app.prasan.spacexwiki.contract.IRemoteDataSource
import app.prasan.spacexwiki.contract.IRepository
import app.prasan.spacexwiki.exception.NoNetworkAvailableException
import app.prasan.spacexwiki.expectactual.PlatformKtorClientEngine
import app.prasan.spacexwiki.remotedatasource.ApplicationWebService
import app.prasan.spacexwiki.repository.ApplicationRepository
import app.prasan.spacexwiki.httpconnection.HttpWebServiceHandler
import app.prasan.spacexwiki.usecase.GetSpaceXCompanyInfoUseCase
import co.touchlab.kermit.Logger
import co.touchlab.kermit.Severity
import co.touchlab.kermit.StaticConfig
import co.touchlab.kermit.platformLogWriter
import io.ktor.client.*
import io.ktor.client.network.sockets.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.cache.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.StringQualifier
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun initKoin(appModule: Module): KoinApplication {
    val koinApplication = startKoin {
        modules(
            coreModule,
            platformModule,
            appModule
        )
    }

    // Dummy initialization logic, making use of appModule declarations for demonstration purposes.
    val koin = koinApplication.koin
    // doOnStartup is a lambda which is implemented in Swift on iOS side
    val doOnStartup = koin.get<() -> Unit>(named("doOnStartup"))
    doOnStartup.invoke()

    val kermit = koin.get<Logger> { parametersOf(null) }
    val appId = koin.get<String>(named("bundle_identifier"))
    kermit.log(Severity.Debug, message = "App Id ---- $appId", throwable = null)

    return koinApplication
}

private val coreModule = module {

    single(named("BaseUrl")) {
        "api.spacexdata.com/v4"
    }

    single {
        HttpClient(get<PlatformKtorClientEngine>().factory()) {

            expectSuccess = false

            install(HttpCache)

            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }

            install(HttpTimeout) {
                socketTimeoutMillis = 2000
                connectTimeoutMillis = 2000
                connectTimeoutMillis = 2000
            }

            install(Logging) {
                level = LogLevel.HEADERS
                logger = object: io.ktor.client.plugins.logging.Logger {
                    override fun log(message: String) {
                        get<Logger>(parameters = {parametersOf("Ktor-Log")}).log(Severity.Debug, "KtorLogging", null, message)
                    }
                }
            }

            install(HttpRequestRetry) {
                maxRetries = 3
                retryOnExceptionIf { _, cause ->
                    cause is NoNetworkAvailableException || cause is SocketTimeoutException
                }
                delayMillis { retry ->
                    retry * 2000L
                } // retries in 2, 4, 8, etc. seconds
            }

            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = get(StringQualifier("BaseUrl"))
                    /*parameters.append("appid", apiKey)
                    parameters.append("exclude", "minutely,hourly")*/
                }
            }
        }
    }

    singleOf(::ApplicationWebService) {
        bind<IRemoteDataSource>()
    }

    singleOf(::ApplicationRepository) {
        bind<IRepository>()
    }

    singleOf(::GetSpaceXCompanyInfoUseCase)

    // platformLogWriter() is a relatively simple config option, useful for local debugging. For production
    // uses you *may* want to have a more robust configuration from the native platform. In KaMP Kit,
    // that would likely go into platformModule expect/actual.
    // See https://github.com/touchlab/Kermit
    val baseLogger = Logger(config = StaticConfig(logWriterList = listOf(platformLogWriter())), "SpaceX-KMM")
    factory { (tag: String?) -> if (tag != null) baseLogger.withTag(tag) else baseLogger }
    single { HttpWebServiceHandler(get(), get(), get<Logger> { parametersOf(null) }) }
}

// Simple function to clean up the syntax a bit
fun KoinComponent.injectLogger(tag: String): Lazy<Logger> = inject { parametersOf(tag) }

expect val platformModule: Module