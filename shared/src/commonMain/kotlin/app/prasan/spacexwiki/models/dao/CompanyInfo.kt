package app.prasan.spacexwiki.models.dao

import app.prasan.spacexwiki.utils.Dollar

data class CompanyInfo(
    val ceo: String,
    val cto: String,
    val ctoPropulsion: String,
    val coo: String,
    val employees: Int,
    val founded: Int,
    val founder: String,
    val launchSites: Int,
    val headquarters: Headquarters,
    val links: Links,
    val name: String,
    val summary: String,
    val testSites: Int,
    val valuation: Dollar,
    val vehicles: Int,
)

data class Headquarters(
    val address: String,
    val city: String,
    val state: String
)

data class Links(
    val elonTwitter: String,
    val flickr: String,
    val twitter: String,
    val website: String
)
