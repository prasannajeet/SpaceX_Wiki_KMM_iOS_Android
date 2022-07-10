package app.prasan.spacexwiki.android

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.prasan.companyinfo.mvi.CompanyInfoSideEffect
import app.prasan.companyinfo.presentation.CompanyInfoViewModel
import app.prasan.spacexwiki.android.compose.SetupNavGraph
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.orbitmvi.orbit.viewmodel.observe

class MainActivity : AppCompatActivity() {
    private val companyInfoViewModel: CompanyInfoViewModel by viewModel()
    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        companyInfoViewModel.observe(
            lifecycleOwner = this,
            sideEffect = ::handleSideEffect
        )
        setContent {
            navController = rememberNavController()
            SpaceXWikiTheme {
                SetupNavGraph(
                    navController = navController,
                    companyInfoViewModel = companyInfoViewModel
                )
            }
        }
    }

    private fun handleSideEffect(sideEffect: CompanyInfoSideEffect) {
        when (sideEffect) {
            is CompanyInfoSideEffect.Toast -> Toast.makeText(
                this,
                sideEffect.text,
                Toast.LENGTH_SHORT
            ).show()
            CompanyInfoSideEffect.BackPress -> this.finish()
            CompanyInfoSideEffect.ApiError -> {
                this.finish()
            }
        }
    }
}
