package com.nytimes.sample.di

import com.gobinda.mvp.sample.ui.flightevent.NewsModule
import com.nytimes.sample.ui.news.NYNewsMainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Activity Binding module, it uses @ContributesAndroidInjector.
 * It will be used to create subComponent Scope Instance and  the life
 * of the scope instance which like as long as the activity & Fragment.
 */

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(NewsModule::class))
    internal abstract fun nyMainActivity(): NYNewsMainActivity

}