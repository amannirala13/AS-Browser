package amansoftdevelopers.com.asbrowser

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.URLUtil
import android.webkit.WebView
import android.webkit.WebViewClient
import android.webkit.WebSettings
import android.widget.Toast


class web_view : AppCompatActivity() {
    private var webView: WebView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        webView = findViewById(R.id.wv)

        //WebSettings
        val webSettings = webView!!.getSettings()
        webSettings.setJavaScriptEnabled(true)
        webSettings.setSupportMultipleWindows(true)
        webView!!.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT)

        //WebClient
        webView!!.webViewClient = WebViewClient()

        //Load URL
        var linkurl: String = intent.extras.getString("args")
        webView!!.loadUrl(
                if (URLUtil.isValidUrl(linkurl))
                    linkurl
        else
            "http://" + linkurl
        )
        var toast: Toast=Toast.makeText(applicationContext,"loading...   "+webView!!.url.toString(), Toast.LENGTH_SHORT)
        toast.setMargin(50F,50F)
        toast.show()

    }

    override fun onBackPressed() {
        if (webView!!.canGoBack()) {
            var toast: Toast = Toast.makeText(applicationContext, "Back...", Toast.LENGTH_SHORT)
            toast.setMargin(50F, 50F)
            toast.show()
            webView!!.goBack()
        } else {
            var toast: Toast=Toast.makeText(applicationContext,"Home...", Toast.LENGTH_SHORT)
            toast.setMargin(50F,50F)
            toast.show()
            super.onBackPressed()
        }
    }
}