package com.gobinda.mvp.sample.ui.flightevent

import com.nytimes.sample.data.DataSource
import com.nytimes.sample.di.ActivityScope
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

/**
 */
@ActivityScope
class NewsPresenter @Inject constructor(private val dataSource: DataSource)
    : NewsContract.Presenter {

    private val disposable = CompositeDisposable()
    private lateinit var view: NewsContract.View

    private var _pageCount = 0

    override val pageCount: Int
        get() = _pageCount


    override fun subscribe(view: NewsContract.View) {
        this.view = view
//        disposable.add(dbOrApi())
    }



    override fun unSubscribe() {
        disposable.clear()
    }

    override fun requestRefresh() {
        disposable.add(api(true))
    }



//
//    private fun dbOrApi(): Disposable {
//        return dataSource.database.flightEventDao().loadFlightEvents()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                if (it.isNotEmpty()) {
//                    view.showFlights(it)
//                    view.showLoading(false)
//                    _pageCount ++;
//                } else {
//                    api()
//                }
//
//            }, { e -> Timber.e(e) })
//    }




    private fun api(refresh: Boolean): Disposable {
        view.showLoading(refresh)
        return dataSource.api.getData(7,"Qj927MHKVm64g09jfHvLpI6cqMgnjtWW")
//        return dataSource.api.getData(/*7,"az2kk489sa347aaa4nn431aa8k"*/)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.showNews(it.results!!)
                view.showLoading(false)
                if(refresh) {
                    _pageCount = 1
                } else {
                    _pageCount++;
                }
                /*val flightDao = dataSource.database.flightEventDao()
                flightDao.deleteAllFlightEvents()
                    .subscribe({
                        Timber.d("Flight Event delete succeed")
                        flightDao.insertFlightEvents(it)
                            .subscribe({
                                Timber.d("Flight Event new records store succeed")
                                flightDao.loadFlightEvents()
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe({
                                        view.showFlights(it)
                                        view.showLoading(false)
                                    }, { e -> Timber.e(e) })

                            }, { e -> Timber.e(e) })
                    }, { e -> Timber.e(e) })*/

            }, { e ->
                Observable.just(e)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        view.showError()
                        view.showLoading(false)
                    }
                Timber.e(e)
            })
    }


    override fun onLoadNextRequest() {
        api(false)
    }
}
