package com.gobinda.mvp.sample.ui.flightevent

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.nytimes.sample.R
import com.nytimes.sample.data.model.News
import com.nytimes.sample.di.ActivityScope
import com.nytimes.sample.ui.adapter.NewsAdapter
import com.nytimes.sample.ui.news.DetailActivity
import com.nytimes.sample.util.Constants
import com.nytimes.sample.util.showSnackBar
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.frag_news_list.*
import timber.log.Timber
import java.util.*
import javax.inject.Inject


/**
 * This fragment class to display list of news
 */
@ActivityScope
class NewsListFragment @Inject constructor() : DaggerFragment(), NewsContract.View {

    @Inject
    override lateinit var presenter: NewsContract.Presenter

    private val newsAdapter = NewsAdapter(ArrayList(0),
        { showDetail(it) })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_news_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUiAndListener()


    }

    private fun setupUiAndListener() {
        with(newsRecyclerView) {
            setHasFixedSize(true)
            adapter = newsAdapter
            val manager = LinearLayoutManager(context)
            layoutManager = manager
            /*addItemDecoration(DividerItemDecoration(context,manager.orientation))*/
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val visibleItemCount = recyclerView.layoutManager!!.childCount
                    val totalItemCount = recyclerView.layoutManager!!.itemCount
                    val firstVisibleItemPosition =
                        (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                    if (!pullToRefresh.isRefreshing() && presenter.count < Constants.ITEM_PAGE_COUNT) {
                        if (
                            visibleItemCount + firstVisibleItemPosition >= totalItemCount - Constants.ITEM_PAGE_MARGIN
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= Constants.ITEM_PAGE_MARGIN
                        ) {
                            presenter.lazyLoadRequest()
                        }
                    }
                }
            })

        }

        pullToRefresh.setOnRefreshListener {
            presenter.requestRefresh()
        }

        emptyView.setOnClickListener {
            showEmptyView(false)
            presenter.requestRefresh()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.subscribe(this)
        if (presenter.count == 0) {
            presenter.requestRefresh()
        }
    }

    override fun onPause() {
        super.onPause()
        presenter.unSubscribe()
    }


    override fun showLoading(active: Boolean) {
        if (active)
            pullToRefresh?.takeIf {
                !it.isRefreshing
            }?.apply { isRefreshing = true } else {
            pullToRefresh?.takeIf {
                it.isRefreshing
            }?.apply { isRefreshing = false }

        }
    }


    override fun showNews(news: List<News>) {
        Timber.d("News  size %s", news.size)
        newsAdapter.addNews(news, presenter.count == 1)
        showEmptyView(false)
    }


    override fun showError() {
        if (newsAdapter.newsList.isNullOrEmpty()) {
            showEmptyView(true)
        } else {
            view?.showSnackBar(getString(R.string.msg_failed_to_refresh), Snackbar.LENGTH_LONG)
        }
    }

    private fun showEmptyView(flag: Boolean) {
        if (flag) {
            emptyView?.visibility = View.VISIBLE
            newsRecyclerView?.visibility = View.GONE
        } else {
            emptyView?.visibility = View.GONE
            newsRecyclerView?.visibility = View.VISIBLE

        }
    }


    private fun showDetail(news: News) {
        startActivity(Intent(activity, DetailActivity::class.java)
            .also { it.putExtra("news", news) })
    }


}