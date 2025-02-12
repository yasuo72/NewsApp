package ro.si.newsapp

data class NewsArticle(
    val title: String,
    val description: String?,
    val urlToImage: String?,
    val url: String
)