package app.prasan.spacexwiki.repository

import app.prasan.spacexwiki.contract.IRemoteDataSource
import app.prasan.spacexwiki.contract.IRepository
import app.prasan.spacexwiki.models.dao.CompanyInfo
import app.prasan.spacexwiki.models.datamapper.CompanyInfoResponseMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


internal class ApplicationRepository(
    //private val localDataSource: ILocalDataSource,
    private val remoteDataSource: IRemoteDataSource,
    private val companyInfoResponseMapper: CompanyInfoResponseMapper,
) : IRepository {
    override suspend fun getSpaceXCompanyInfo(forceRefresh: Boolean): Flow<Result<CompanyInfo>> {
        return remoteDataSource.getSpaceXCompanyInfo().map { result ->
            when (result.isSuccess) {
                true -> {
                    result.getOrNull()?.let { response ->
                        Result.success(companyInfoResponseMapper(response))
                    }
                        ?: Result.failure(IllegalStateException("Data should not be null for success"))
                }

                false -> {
                    result.exceptionOrNull()?.let { throwable ->
                        Result.failure(throwable)
                    }
                        ?: Result.failure(IllegalStateException("Exception should not be null for failure"))
                }
            }
        }
    }
}