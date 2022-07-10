package app.prasan.androidcore.compose.composable

import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@Composable
fun ComposeWebView(useAccompanist: Boolean, title: String, url: String, onBackPressed: () -> Unit) {
    BackHandler(onBack = onBackPressed)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.button,
                        fontSize = 16.sp,
                        color = if(isSystemInDarkTheme()) Color.White else Color.Gray
                    )
                },
                backgroundColor = if(isSystemInDarkTheme()) Color.Gray else Color.White,
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Back button",
                            tint = if(isSystemInDarkTheme()) Color.White else Color.Gray
                        )
                    }
                }
            )
        },
    )  { paddingValues ->
        if(useAccompanist) {
            val urlState = rememberWebViewState(url)
            WebView(
                state = urlState,
                captureBackPresses = false,
                client = object : AccompanistWebViewClient() {
                    override fun shouldOverrideUrlLoading(
                        view: WebView?,
                        request: WebResourceRequest?
                    ): Boolean {
                        return false
                    }
                },
                onCreated = {
                    it.settings.run {
                        domStorageEnabled = true
                        cacheMode = WebSettings.LOAD_DEFAULT
                        javaScriptEnabled = true
                    }
                }
            )
        } else {
            AndroidView(
                modifier = Modifier
                    .background(Color.Green)
                    .padding(paddingValues),
                factory = { context ->
                    WebView(context).apply {
                        settings.run {
                            domStorageEnabled = true
                            cacheMode = WebSettings.LOAD_DEFAULT
                            javaScriptEnabled = true
                        }

                        webViewClient = object : WebViewClient() {
                            override fun shouldOverrideUrlLoading(
                                view: WebView?,
                                request: WebResourceRequest?
                            ): Boolean {
                                return false
                            }
                        }
                    }
                },
                update = {
                    it.loadUrl(url)
                },
            )
        }
    }
}