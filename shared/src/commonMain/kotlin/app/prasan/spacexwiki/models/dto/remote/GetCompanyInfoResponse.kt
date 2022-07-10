package app.prasan.spacexwiki.models.dto.remote


import app.prasan.spacexwiki.utils.Dollar
import app.prasan.spacexwiki.utils.DollarSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCompanyInfoResponse(
    val ceo: String = "",
    val coo: String = "",
    val cto: String = "",
    @SerialName("cto_propulsion")
    val ctoPropulsion: String = "",
    val employees: Int = 0,
    val founded: Int = 0,
    val founder: String = "",
    val headquarters: Headquarters,
    val id: String = "",
    @SerialName("launch_sites")
    val launchSites: Int = 0,
    val links: Links,
    val name: String = "",
    val summary: String = "",
    @SerialName("test_sites")
    val testSites: Int = 0,
    @Serializable(with = DollarSerializer::class)
    val valuation: Dollar = Dollar.ZERO,
    val vehicles: Int = 0
) {
    @Serializable
    data class Headquarters(
        val address: String,
        val city: String,
        val state: String
    )

    @Serializable
    data class Links(
        @SerialName("elon_twitter")
        val elonTwitter: String,
        val flickr: String,
        val twitter: String,
        val website: String
    )
}