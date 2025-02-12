package ro.si.newsapp




import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        webView = findViewById(R.id.web_view)
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true // Enable JavaScript if needed

        val articleUrl = intent.getStringExtra("ARTICLE_URL")

        if (articleUrl != null && articleUrl.isNotEmpty()) {
            webView.loadUrl(articleUrl)
        } else {
            Toast.makeText(this, "Invalid URL", Toast.LENGTH_SHORT).show()
            finish() // Close the activity if URL is invalid
        }
    }
}