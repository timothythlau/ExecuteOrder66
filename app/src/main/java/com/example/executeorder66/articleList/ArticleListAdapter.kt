package com.example.executeorder66.articleList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.executeorder66.R
import com.example.executeorder66.network.models.ChildDataResponse
import com.example.executeorder66.network.models.ChildResponse
import kotlinx.android.synthetic.main.article_card_view.view.*

class ArticleListAdapter(private val clickHandler: ArticleOnClickHandler) :
    RecyclerView.Adapter<ArticleViewHolder>() {
    var articleList: List<ChildResponse> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.article_card_view,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bindView(articleList[position], clickHandler)
    }
}

class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindView(item: ChildResponse, clickHandler: ArticleOnClickHandler) {
        val articleData = item.data?.also { data ->
            itemView.title.text = data.title

            // test code to test thumbnails, as the Reddit API thumbnail is a bit broken right now
//            val random = Math.random()
//            val testThumb = when {
//                random > 0.6 && random <=0.8 -> "https://i.kym-cdn.com/entries/icons/original/000/022/877/87d.jpg"
//                random > 0.8 -> "https://vignette.wikia.nocookie.net/starwars/images/9/9a/Palp_trustme.jpg/revision/latest?cb=20070114040526"
//                else -> ""
//            }
//
//            itemView.thumbnail.visibility =
//                (if (testThumb.isNotEmpty()) View.VISIBLE else View.GONE).also {
//                    if (it == View.VISIBLE) {
//                        Glide.with(itemView)
//                            .load(testThumb)
//                            .into(itemView.thumbnail)
//                    }
//                }

            itemView.thumbnail.visibility =
                (if (data.thumbnail?.isNotEmpty() == true) View.VISIBLE else View.GONE).also {
                    if (it == View.VISIBLE) {
                        Glide.with(itemView)
                            .load(data.thumbnail)
                            .into(itemView.thumbnail)
                    }
                }
        }

        itemView.setOnClickListener { clickHandler.onArticleClicked(articleData) }
    }
}

interface ArticleOnClickHandler {
    fun onArticleClicked(article: ChildDataResponse?)
}