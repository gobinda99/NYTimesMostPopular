package com.gobinda.mvp.sample.ui.flightevent

import com.nytimes.sample.BuildConfig
import com.nytimes.sample.data.DataSource
import com.nytimes.sample.di.ActivityScope
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

/**
 * News Presenter Instance class of MVP
 */
@ActivityScope
class NewsPresenter @Inject constructor(private val dataSource: DataSource)
    : NewsContract.Presenter {

    private val disposable = CompositeDisposable()
    private lateinit var view: NewsContract.View

    private var _count = 0
    private var apiCalling = false;

    override val count: Int
        get() = _count


    override fun subscribe(view: NewsContract.View) {
        this.view = view
    }


    override fun unSubscribe() {
        disposable.clear()
    }

    override fun requestRefresh() {
        if (!apiCalling) {
            apiCalling = true
            disposable.add(api(true))
        }
    }


    private fun api(refresh: Boolean): Disposable {
        view.showLoading(refresh)
        return dataSource.api.getNews(getPeriod(refresh), BuildConfig.API_KEY)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                apiCalling = false
                view.showNews(it.results!!)
                view.showLoading(false)
                if (refresh) {
                    _count = 1
                } else {
                    _count++;
                }
            }, { e ->
                apiCalling = false
                view.showError()
                view.showLoading(false)
                Timber.e(e)
            })
    }

    private fun getPeriod(refresh: Boolean): Int {
        var period: Int = -1
        if (refresh || count == 0) {
            period = 1;
        } else if (count == 1) {
            period = 7
        } else if (count == 2) {
            period = 30
        }
        return period
    }


    override fun lazyLoadRequest() {
        if (!apiCalling) {
            apiCalling = true
            api(false)
        }
    }
}
