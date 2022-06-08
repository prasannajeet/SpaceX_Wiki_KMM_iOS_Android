package app.prasan.spacexwiki.repository

import app.prasan.spacexwiki.contract.ILocalDataSource
import app.prasan.spacexwiki.contract.IRemoteDataSource
import app.prasan.spacexwiki.contract.IRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


internal class ApplicationRepository(
    //private val localDataSource: ILocalDataSource,
    private val remoteDataSource: IRemoteDataSource,
): IRepository {
    override suspend fun getSpaceXCompanyInfo(): Flow<Result<String>> {
        return remoteDataSource.getSpaceXCompanyInfo()
    }

}