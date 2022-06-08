package app.prasan.spacexwiki.httpconnection

import app.prasan.spacexwiki.exception.NoNetworkAvailableException
import app.prasan.spacexwiki.expectactual.ConnectivityManager
import co.touchlab.kermit.Logger
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.utils.*
import io.ktor.http.*
import io.ktor.http.ContentType.*
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow


internal class HttpWebServiceHandler  constructor(
    client: HttpClient,
    connectivityInfoManager: ConnectivityManager,
    private val logger: Logger
) {
    @PublishedApi internal val httpClient = client
    @PublishedApi internal val connectivityMgr = connectivityInfoManager

    suspend inline fun <reified NetworkResponse : Any> performHttpConnection(
        httpConnectionType: HttpConnectivityType,
    ): Flow<Result<NetworkResponse>> {


        return flow {
            when {
                connectivityMgr.isNetworkAvailable() -> {

                    val response = httpClient.run {

                        request {
                            url {
                                encodedPath = httpConnectionType.path
                                if(httpConnectionType is HttpConnectivityType.GET) {
                                    httpConnectionType.queryParams.forEach { entry ->
                                        parameters.append(entry.key, entry.value)
                                    }
                                }
                            }

                            method = httpConnectionType.method

                            setBody(
                                when (httpConnectionType) {
                                    is HttpConnectivityType.MULTIPART -> {
                                        contentType(MultiPart.Any)
                                        MultiPartFormDataContent(httpConnectionType.multiPart)
                                    }

                                    is HttpConnectivityType.PATCH -> {
                                        contentType(Application.Json)
                                        httpConnectionType.request
                                    }

                                    is HttpConnectivityType.POST -> {
                                        contentType(Application.Json)
                                        httpConnectionType.request
                                    }

                                    is HttpConnectivityType.PUT -> {
                                        contentType(Application.Json)
                                        httpConnectionType.request
                                    }

                                    else -> EmptyContent
                                }
                            )
                        }
                    }

                    logger.d {
                        "Response code is ${response.status}"
                    }

                    when (response.status) {
                        HttpStatusCode.NoContent -> throw IllegalStateException("Cannot get 204 as status code")
                        HttpStatusCode.OK -> {
                            val result = response.body<NetworkResponse>()
                            logger.d { "Sending success to UI" }
                            emit(Result.success(result))
                        }
                        else -> throw IOException("Received error from server with code ${response.status}")
                    }
                    httpClient.close()
                    return@flow
                }
                else -> throw NoNetworkAvailableException

            }
        }.catch { exception ->
            logger.d {
                "Exception is ${exception}"
            }
            emit(Result.failure(exception))
            return@catch
        }.cancellable()
    }
}