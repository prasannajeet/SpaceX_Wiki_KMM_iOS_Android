package app.prasan.androidcore.compose.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import app.prasan.androidcore.compose.theme.DragonTheme

@Composable
fun TranslucentInfoCard(
    title: String,
    icon: ImageVector,
    onIconClick: () -> Unit = {},
    content: @Composable () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Color.Transparent,
        elevation = 4.dp,
        shape = RoundedCornerShape(10.dp)
    ) {

        Column(
            modifier = Modifier
                .background(DragonTheme.colors.ornament.primary)
                .padding(16.dp),
        ) {
            Row {
                Text(
                    text = title,
                    color = DragonTheme.colors.brand.primary,
                    style = DragonTheme.typography.title
                )
                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    modifier = Modifier.clickable(onClick = onIconClick),
                    imageVector = icon,
                    tint = Color.White,
                    contentDescription = ""
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            content()
        }
    }
}