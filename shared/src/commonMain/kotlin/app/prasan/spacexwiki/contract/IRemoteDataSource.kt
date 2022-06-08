package app.prasan.spacexwiki.contract

import kotlinx.coroutines.flow.Flow

interface IRemoteDataSource {
    suspend fun getSpaceXCompanyInfo(): Flow<Result<String>>
}