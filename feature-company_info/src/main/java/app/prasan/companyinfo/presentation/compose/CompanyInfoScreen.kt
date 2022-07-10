package app.prasan.companyinfo.presentation.compose

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.prasan.androidcore.R
import app.prasan.androidcore.compose.composable.TranslucentInfoCard
import app.prasan.androidcore.compose.theme.DragonTheme
import app.prasan.companyinfo.mvi.CompanyInfoState
import app.prasan.spacexwiki.android.SpaceXWikiTheme

@Composable
fun CompanyInfoScreen(
    state: CompanyInfoState,
    onBackPress: () -> Unit,
    onLaunch: () -> Unit,
) {
    BackHandler(onBack = onBackPress)
    LaunchedEffect(key1 = "state") {
        onLaunch()
    }
    CompanyInfoContent(state = state)
}

@Composable
fun CompanyInfoContent(
    state: CompanyInfoState,
) {

    state.data?.let { response ->
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillHeight,
            painter = painterResource(id = R.drawable.launch_bg), contentDescription = ""
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DragonTheme.colors.background.primary)
                .padding(top = 16.dp, bottom = 64.dp, start = 16.dp, end = 16.dp),
        ) {
            TranslucentInfoCard(title = response.name, icon = Icons.Default.Info) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "Founded",
                            color = DragonTheme.colors.foreground.secondary,
                            style = DragonTheme.typography.subTitle
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = response.founded.toString(),
                            color = DragonTheme.colors.foreground.secondary,
                            style = DragonTheme.typography.body
                        )
                    }
                    Column {
                        Text(
                            text = "Valuation",
                            color = DragonTheme.colors.foreground.secondary,
                            style = DragonTheme.typography.subTitle
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "$${response.valuation}",
                            color = DragonTheme.colors.foreground.secondary,
                            style = DragonTheme.typography.body
                        )
                    }
                    Column {
                        Text(
                            text = "Employees",
                            color = DragonTheme.colors.foreground.secondary,
                            style = DragonTheme.typography.subTitle
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = response.employees.toString(),
                            color = DragonTheme.colors.foreground.secondary,
                            style = DragonTheme.typography.body
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "Vehicles",
                            color = DragonTheme.colors.foreground.secondary,
                            style = DragonTheme.typography.subTitle
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = response.vehicles.toString(),
                            color = DragonTheme.colors.foreground.secondary,
                            style = DragonTheme.typography.body
                        )
                    }
                    Column {
                        Text(
                            text = "Launch Sites",
                            color = DragonTheme.colors.foreground.secondary,
                            style = DragonTheme.typography.subTitle
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = response.launchSites.toString(),
                            color = DragonTheme.colors.foreground.secondary,
                            style = DragonTheme.typography.body
                        )
                    }
                    Column {
                        Text(
                            text = "Test Sites",
                            color = DragonTheme.colors.foreground.secondary,
                            style = DragonTheme.typography.subTitle
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = response.testSites.toString(),
                            color = DragonTheme.colors.foreground.secondary,
                            style = DragonTheme.typography.body
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Summary",
                    color = DragonTheme.colors.foreground.secondary,
                    style = DragonTheme.typography.subTitle
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = response.summary,
                    color = DragonTheme.colors.foreground.secondary,
                    style = DragonTheme.typography.body
                )

                /*UrlImageLoader(
                    modifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth(),
                    placeholder = painterResource(id = R.drawable.spacex_logo),
                    imgUrl = "https://media.vanityfair.com/photos/5a7b1800a7cf14041ee0af07/16:9/w_1280,c_limit/t-elon-musk-showman-rocket.jpg",
                    contentScale = UrlImageLoaderContentScale.Crop
                )*/

            }

            Spacer(modifier = Modifier.height(16.dp))

            TranslucentInfoCard(title = "Headquarters", icon = Icons.Default.LocationOn) {
                Text(
                    text = "${response.headquarters.address}, ${response.headquarters.city}, ${response.headquarters.state}, USA",
                    color = DragonTheme.colors.foreground.secondary,
                    style = DragonTheme.typography.body
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            TranslucentInfoCard(title = "Board", icon = Icons.Default.AccountCircle) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Founder",
                            color = DragonTheme.colors.foreground.secondary,
                            style = DragonTheme.typography.body
                        )
                        Text(
                            text = response.founder,
                            color = DragonTheme.colors.foreground.secondary,
                            style = DragonTheme.typography.body
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "CTO",
                            color = DragonTheme.colors.foreground.secondary,
                            style = DragonTheme.typography.body
                        )

                        Text(
                            text = response.cto,
                            color = DragonTheme.colors.foreground.secondary,
                            style = DragonTheme.typography.body
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "CEO",
                            color = DragonTheme.colors.foreground.secondary,
                            style = DragonTheme.typography.body
                        )

                        Text(
                            text = response.ceo,
                            color = DragonTheme.colors.foreground.secondary,
                            style = DragonTheme.typography.body
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "COO",
                            color = DragonTheme.colors.foreground.secondary,
                            style = DragonTheme.typography.body
                        )

                        Text(
                            text = response.coo,
                            color = DragonTheme.colors.foreground.secondary,
                            style = DragonTheme.typography.body
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Propulsion CTO",
                            color = DragonTheme.colors.foreground.secondary,
                            style = DragonTheme.typography.body
                        )

                        Text(
                            text = response.ctoPropulsion,
                            color = DragonTheme.colors.foreground.secondary,
                            style = DragonTheme.typography.body
                        )
                    }
                }
            }

        }
    }

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}

@Preview(name = "Dark Mode Loading", uiMode = UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun CompanyInfoContentDarkLoading() {
    SpaceXWikiTheme {
        CompanyInfoContent(state = CompanyInfoState(isLoading = true))
    }
}

@Preview(name = "Light Mode Loading", uiMode = UI_MODE_NIGHT_NO, showSystemUi = true)
@Composable
fun CompanyInfoContentLightLoading() {
    SpaceXWikiTheme {
        CompanyInfoContent(state = CompanyInfoState())
    }
}
