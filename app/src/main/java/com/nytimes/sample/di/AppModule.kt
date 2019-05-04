package com.nytimes.sample.di

import android.content.Context
import com.nytimes.sample.App
import dagger.Binds
import dagger.Module


/**
 * App Module
 */
@Module
abstract class AppModule {

    /**
     * Expose Application as an injectable context
     */
    @Binds
    internal abstract fun bindContext(app: App): Context


}