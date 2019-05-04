package com.nytimes.sample.ui

/**
 * Base View interface class of MVP
 */
interface BaseView<T> {
    var presenter : T
}