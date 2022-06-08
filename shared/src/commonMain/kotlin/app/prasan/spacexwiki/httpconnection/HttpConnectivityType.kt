package app.prasan.spacexwiki.httpconnection

import io.ktor.client.utils.*
import io.ktor.content.ByteArrayContent
import io.ktor.http.*
import io.ktor.http.content.*

sealed class HttpConnectivityType(open val path: String, val method: HttpMethod) {
    class GET(override val path: String, val queryParams: Map<String, String> = emptyMap()) : HttpConnectivityType(path, HttpMethod.Get)
    class POST(override val path: String, val request: Any = EmptyContent) :
        HttpConnectivityType(path, HttpMethod.Post)

    class PUT(override val path: String, val request: ByteArrayContent) :
        HttpConnectivityType(path, HttpMethod.Put)

    class PATCH(override val path: String, val request: Any = EmptyContent) :
        HttpConnectivityType(path, HttpMethod.Patch)

    class DELETE(override val path: String) : HttpConnectivityType(path, HttpMethod.Delete)
    class MULTIPART(override val path: String, val multiPart: List<PartData>) :
        HttpConnectivityType(path, HttpMethod.Post)
}