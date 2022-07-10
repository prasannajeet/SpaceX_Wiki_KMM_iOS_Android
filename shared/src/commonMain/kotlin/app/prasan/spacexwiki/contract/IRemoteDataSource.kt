package app.prasan.spacexwiki.contract

import app.prasan.spacexwiki.models.dto.remote.GetCompanyInfoResponse
import kotlinx.coroutines.flow.Flow

interface IRemoteDataSource {
    suspend fun getSpaceXCompanyInfo(): Flow<Result<GetCompanyInfoResponse>>
}