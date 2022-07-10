package app.prasan.spacexwiki.android.compose


sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object CompanyInfo : Screen("company_info_screen")
}