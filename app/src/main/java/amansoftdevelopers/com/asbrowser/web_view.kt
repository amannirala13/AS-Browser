package amansoftdevelopers.com.asbrowser

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.webkit.*
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.Toast


class web_view : AppCompatActivity() {
    private var webView: WebView? = null
    private var progressBar: ProgressBar? = null
    private var frameL: FrameLayout?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        //ID store
        webView = findViewById(R.id.wv)
        progressBar = findViewById(R.id.progressBar)
        frameL=findViewById(R.id.frameid)
        progressBar!!.max = 100

        //WebClient
        webView!!.webViewClient=WebViewClient()
        webView!!.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                frameL!!.visibility=View.VISIBLE
                progressBar!!.visibility = View.VISIBLE
                progressBar!!.progress = newProgress
                if (newProgress == 100) {
                    frameL!!.visibility = View.INVISIBLE
                    progressBar!!.visibility = View.INVISIBLE
                }
            }
        }
        //WebSettings
        webView!!.settings.javaScriptEnabled=true
        webView!!.settings.javaScriptCanOpenWindowsAutomatically=true
        webView!!.settings.setSupportMultipleWindows(true)
        webView!!.settings.cacheMode = WebSettings.LOAD_DEFAULT

        //Load URL
        var linkurl: String = intent.extras.getString("args")
        webView!!.loadUrl(
                if (URLUtil.isValidUrl(linkurl))
                    linkurl
        else
            "http://" + linkurl
        )
        var toast: Toast=Toast.makeText(applicationContext,"loading...   "+webView!!.url.toString(), Toast.LENGTH_SHORT)
        toast.setMargin(-50F,0F)
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
