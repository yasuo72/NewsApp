package ro.si.newsapp


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter
    private val repository = Repository()
    private val apiKey = "197ae23677cc4a1c8d8aeba319d4ee9b" // Replace with your News API key

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up Toolbar
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Set up RecyclerView
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchNews()

        // Set up Bottom Navigation
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    // Handle home click
                    true
                }
                R.id.nav_favorites -> {
                    // Handle favorites click
                    true
                }
                R.id.nav_settings -> {
                    // Handle settings click
                    true
                }
                else -> false
            }
        }
    }

    private fun fetchNews() {
        repository.getTopHeadlines(apiKey, "the-times-of-india,ndtv,hindustan-times").enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    newsAdapter = NewsAdapter(response.body()!!.articles, this@MainActivity)
                    recyclerView.adapter = newsAdapter
                } else {
                    Log.e("MainActivity", "Error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("MainActivity", "Failed to fetch news: ${t.message}")
            }
        })
    }
}