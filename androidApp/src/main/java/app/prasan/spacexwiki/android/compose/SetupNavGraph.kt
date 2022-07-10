package app.prasan.spacexwiki.android.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import app.prasan.companyinfo.presentation.CompanyInfoViewModel
import app.prasan.companyinfo.presentation.compose.CompanyInfoScreen

@Composable
fun SetupNavGraph(navController: NavHostController, companyInfoViewModel: CompanyInfoViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen {
                navController.navigate(route = Screen.CompanyInfo.route)
            }
        }

        composable(route = Screen.CompanyInfo.route) {
            CompanyInfoScreen(
                state = companyInfoViewModel.container.stateFlow.collectAsState().value,
                onBackPress = { companyInfoViewModel.onBackPress() }) {
                companyInfoViewModel.getCompanyInfo()
            }
        }
    }
}