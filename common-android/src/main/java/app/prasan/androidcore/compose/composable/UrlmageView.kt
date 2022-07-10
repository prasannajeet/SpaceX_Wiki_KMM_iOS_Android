package app.prasan.androidcore.compose.composable

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

/**
 * Wrapper API for loading images from URLs into composable
 * image views
 * @author Prasan
 * @since 0.1.0
 * @see ContentScale
 * @see AsyncImage
 * @see ImageRequest
 * @param modifier [Modifier] object
 * @param imgUrl URL from which image will be loaded
 * @param contentScale [UrlImageLoaderContentScale] enum value to fit the image
 * @param placeholder Placeholder [Painter] to show while image is loading, defaulted to null
 * @param contentDescription ADA compliance content description, defaulted to empty string
 */
@Composable
fun UrlImageLoader(
    modifier: Modifier = Modifier
        .wrapContentSize()
        .clip(CircleShape),
    imgUrl: String,
    contentScale: UrlImageLoaderContentScale,
    placeholder: Painter? = null,
    contentDescription: String = "Image"
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imgUrl)
            .crossfade(true)
            .build(),
        placeholder = placeholder,
        contentDescription = contentDescription,
        contentScale = contentScale.contentScale,
        modifier = modifier,
    )
}

/**
 * Wrapper URL image loader content scale based off of [ContentScale]
 * @see [ContentScale]
 * @property contentScale [ContentScale] object referred internally
 * @author Prasan
 * @since 0.1.0
 */
@Suppress("Unused")
enum class UrlImageLoaderContentScale(val contentScale: ContentScale) {
    Crop(ContentScale.Crop),
    Fit(ContentScale.Fit),
    FillHeight(ContentScale.FillHeight),
    FillWidth(ContentScale.FillWidth),
    Inside(ContentScale.Inside),
    None(ContentScale.None),
    FillBounds(ContentScale.FillBounds)
}