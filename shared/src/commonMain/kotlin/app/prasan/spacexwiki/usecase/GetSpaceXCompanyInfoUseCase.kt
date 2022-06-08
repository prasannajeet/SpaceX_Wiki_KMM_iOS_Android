package app.prasan.spacexwiki.usecase

import app.prasan.spacexwiki.contract.IRepository
import app.prasan.spacexwiki.contract.IUseCase
import kotlinx.coroutines.flow.Flow

class GetSpaceXCompanyInfoUseCase(
    private val repository: IRepository
): IUseCase<Unit, String> {
    override suspend fun invoke(input: Unit): Flow<Result<String>> {
        return repository.getSpaceXCompanyInfo()
    }
}