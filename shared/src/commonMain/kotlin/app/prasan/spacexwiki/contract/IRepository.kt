package app.prasan.spacexwiki.contract

import app.prasan.spacexwiki.models.dao.CompanyInfo
import kotlinx.coroutines.flow.Flow

/**
 * Designated repository contract
 * @author Prasan
 * @since 1.0
 */
interface IRepository {
    suspend fun getSpaceXCompanyInfo(forceRefresh: Boolean): Flow<Result<CompanyInfo>>
}