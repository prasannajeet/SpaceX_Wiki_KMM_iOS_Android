package app.prasan.spacexwiki.remotedatasource

import app.prasan.spacexwiki.contract.IRemoteDataSource
import app.prasan.spacexwiki.httpconnection.HttpConnectivityType
import app.prasan.spacexwiki.httpconnection.HttpWebServiceHandler
import kotlinx.coroutines.flow.Flow


internal class ApplicationWebService(
    private val httpWebServiceHandler: HttpWebServiceHandler
) : IRemoteDataSource {
    override suspend fun getSpaceXCompanyInfo(): Flow<Result<String>> {
        return httpWebServiceHandler.performHttpConnection(
            HttpConnectivityType.GET(
                "docs"
            )
        )
    }
}