package com.chalat.surveys.base

/**
 *
 * Created by Chalat Chansima on 6/10/18.
 *
 */
interface BaseView<T> {

    fun isActive(): Boolean

    fun setPresenter(presenter: T)

}
