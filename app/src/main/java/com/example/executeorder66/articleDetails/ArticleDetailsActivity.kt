package com.example.executeorder66.articleDetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.executeorder66.R
import com.example.executeorder66.network.models.ChildDataResponse
import kotlinx.android.synthetic.main.activity_article_details.*
import kotlinx.android.synthetic.main.content_article_details.*


class ArticleDetailsActivity : AppCompatActivity() {
    companion object {
        fun Context.articleDetailsActivityIntent(articleData: ChildDataResponse?): Intent {
            return Intent(this, ArticleDetailsActivity::class.java)
                .apply {
                    putExtra(ARTICLE_DATA_KEY, articleData)
                }
        }

        private const val ARTICLE_DATA_KEY = "ARTICLE_DATA_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_details)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        intent.getParcelableExtra<ChildDataResponse>(ARTICLE_DATA_KEY)?.let { article ->
            supportActionBar?.title = article.title

            // fixme: test code for thumbnails
//            val random = Math.random()
//            val testThumb = when {
//                random > 0.6 && random <=0.8 -> "https://i.kym-cdn.com/entries/icons/original/000/022/877/87d.jpg"
//                random > 0.8 -> "https://vignette.wikia.nocookie.net/starwars/images/9/9a/Palp_trustme.jpg/revision/latest?cb=20070114040526"
//                else -> ""
//            }
//
//            thumbnail.visibility = (if (testThumb.isBlank()) View.GONE else View.VISIBLE)
//                .also {
//                    if (it == View.VISIBLE) {
//                        Glide.with(this)
//                            .load(testThumb)
//                            .into(thumbnail)
//                    }
//                }

            thumbnail.visibility = (if (article.thumbnail == null) View.GONE else View.VISIBLE)
                .also {
                    if (it == View.VISIBLE) {
                        Glide.with(this)
                            .load(article.thumbnail.toString())
                            .into(thumbnail)
                    }
                }

            body.text = article.selftext
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
