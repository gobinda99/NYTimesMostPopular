package com.gobinda.mvp.sample.ui.flightevent

import com.nytimes.sample.data.model.Data
import com.nytimes.sample.data.model.News
import com.nytimes.sample.ui.BasePresenter
import com.nytimes.sample.ui.BaseView

/**
 * Flight Event list contract between view and presenter of MVP
 */
interface NewsContract {
    interface Presenter : BasePresenter<View> {
        val count : Int
        fun requestRefresh()
        fun lazyLoadRequest()
    }

    interface View : BaseView<Presenter> {

        fun showLoading(active: Boolean)

        fun showNews(news : List<News>)

        fun showError()

    }
}