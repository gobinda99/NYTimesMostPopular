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
        val pageCount : Int
        fun requestRefresh()
        fun onLoadNextRequest()
    }

    interface View : BaseView<Presenter> {

        fun showLoading(active: Boolean)

        fun showFlights(news : List<News>)

        fun showError()

    }
}