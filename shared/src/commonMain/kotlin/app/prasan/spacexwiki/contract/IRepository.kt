package app.prasan.spacexwiki.contract

import kotlinx.coroutines.flow.Flow

/**
 * Designated repository contract
 * @author Prasan
 * @since 1.0
 */
interface IRepository {
    suspend fun getSpaceXCompanyInfo(): Flow<Result<String>>
}