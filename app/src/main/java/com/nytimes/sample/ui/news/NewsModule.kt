package com.gobinda.mvp.sample.ui.flightevent

import com.nytimes.sample.di.ActivityScope
import com.nytimes.sample.di.FragmentScope
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Flight Event Module which to inject dependencies  fragment and
 * Exposes presenter
 */
@Module
abstract class NewsModule {

    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun flyEventsFragment(): NewsListFragment

    @ActivityScope
    @Binds
    internal abstract fun flyEventsPresenter(presenter: NewsPresenter): NewsContract.Presenter


}