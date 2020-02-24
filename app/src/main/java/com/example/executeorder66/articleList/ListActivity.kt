package com.example.executeorder66.articleList

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.executeorder66.MainApplication
import com.example.executeorder66.R
import com.example.executeorder66.articleDetails.ArticleDetailsActivity.Companion.articleDetailsActivityIntent
import com.example.executeorder66.network.RedditRepository
import com.example.executeorder66.network.Result
import com.example.executeorder66.network.models.ChildDataResponse
import com.example.executeorder66.network.models.RedditResponse
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class ListActivity : AppCompatActivity() {
    companion object {
        const val LIST_SCROLL_STATE = "LIST_SCROLL_STATE"
    }

    @Inject
    lateinit var redditRepository: RedditRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as? MainApplication)?.appComponent?.inject(this)
        setContentView(R.layout.activity_main)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        val adapter = ArticleListAdapter(ArticleAdapterOnClickHandler())
        article_list.adapter = adapter
        article_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        article_list.addItemDecoration(MarginItemDecoration(8.toPx(this)))

        val model = ListActivityViewModel(redditRepository)

        val listObserver = Observer<Result<RedditResponse>> {
            if (it is Result.Success) {
                it.body.data?.children?.let {
                    adapter.articleList = it
                    adapter.notifyDataSetChanged()
                    savedInstanceState?.getParcelable<Parcelable>(LIST_SCROLL_STATE)
                        ?.let { article_list.layoutManager?.onRestoreInstanceState(it) }
                }
            } else {
                // todo: show error
            }
        }

        model.getList().observe(this, listObserver)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(LIST_SCROLL_STATE, article_list.layoutManager?.onSaveInstanceState())
    }

    inner class ArticleAdapterOnClickHandler : ArticleOnClickHandler {
        override fun onArticleClicked(article: ChildDataResponse?) {
            articleDetailsActivityIntent(article).also {
                startActivity(it)
            }
        }
    }
}

fun Int.toPx(context: Context): Int = (this * context.resources.displayMetrics.density).toInt()