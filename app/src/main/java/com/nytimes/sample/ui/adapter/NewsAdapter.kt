package com.nytimes.sample.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nytimes.sample.R
import com.nytimes.sample.data.model.News
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.news_item.*
import timber.log.Timber

/**
 * News Adapter
 */
class NewsAdapter(
    news: List<News>,
    private val itemClick: (News) -> Unit = {}
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    var newsList: MutableList<News> = news.toMutableList()

    fun addNews(news: List<News>, clear: Boolean = false) {
        if (clear) {
            newsList.clear()
        }
        newsList.addAll(news.toMutableList())
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return inflate.inflate(R.layout.news_item, parent, false)
            .run {
                ItemViewHolder(this, itemClick)

            }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).run {
            bind(newsList.get(position) as News)
        }

    }


    override fun getItemCount() = newsList.size


    inner class ItemViewHolder(override val containerView: View, private val itemClick: (News) -> Unit) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(news: News) {
            with(news) {
                media?.takeIf { it.isNotEmpty() }?.get(0)?.takeIf { "image".equals(it.type) }
                    ?.mediaMetadata?.takeIf { it.isNotEmpty() }?.get(0)?.url?.let {
                    Glide.with(itemView).load(it).apply(RequestOptions.circleCropTransform()).into(image);
                }
                titleText.text = title
                byLineText.text = byline
                dateText.text = publishedDate
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }


}