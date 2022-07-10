package app.prasan.spacexwiki.usecase

import app.prasan.spacexwiki.contract.IRepository
import app.prasan.spacexwiki.contract.IUseCase
import app.prasan.spacexwiki.models.dao.CompanyInfo
import kotlinx.coroutines.flow.Flow

class GetSpaceXCompanyInfoUseCase(
    private val repository: IRepository
) : IUseCase<Boolean, CompanyInfo> {
    override suspend fun invoke(input: Boolean): Flow<Result<CompanyInfo>> {
        return repository.getSpaceXCompanyInfo(input)
    }
}