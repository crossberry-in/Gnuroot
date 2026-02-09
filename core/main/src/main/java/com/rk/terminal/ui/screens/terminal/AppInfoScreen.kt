package com.rk.terminal.ui.screens.terminal

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import org.json.JSONObject

@Composable
fun AppInfoScreen(modifier: Modifier = Modifier, navController: NavController) {
    val context = LocalContext.current
    val markdown = remember(context) {
        runCatching {
            context.assets.open("about.md").bufferedReader().use { it.readText() }
        }.getOrElse { "# About\n\nFailed to load content." }
    }

    val html = remember(markdown) {
        val escaped = JSONObject.quote(markdown)
        """
        <!DOCTYPE html>
        <html>
        <head>
            <meta charset="utf-8">
            <script src="https://cdn.jsdelivr.net/npm/marked/marked.min.js"></script>
            <style>
                body { 
                    padding: 16px; 
                    font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, sans-serif;
                    line-height: 1.6;
                }
                code { background: #f0f0f0; padding: 2px 4px; border-radius: 3px; }
                pre { background: #f0f0f0; padding: 10px; border-radius: 5px; overflow-x: auto; }
                img { max-width: 100%; }
                a { color: #0066cc; }
            </style>
        </head>
        <body>
            <div id="content"></div>
            <script>
                try {
                    document.getElementById('content').innerHTML = marked.parse($escaped);
                } catch (e) {
                    document.getElementById('content').innerHTML = '<pre>' + e.message + '</pre>';
                }
            </script>
        </body>
        </html>
        """.trimIndent()
    }

    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { ctx ->
            WebView(ctx).apply {
                settings.javaScriptEnabled = true
                webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                        return false // stay in WebView
                    }
                }
                // Load the HTML
                loadDataWithBaseURL(null, html, "text/html", "utf-8", null)
            }
        },
        update = { webView ->
            // Reload content if needed
        }
    )
}
