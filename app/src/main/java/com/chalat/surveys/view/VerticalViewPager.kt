package com.chalat.surveys.view

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.View
import android.view.MotionEvent




/**
 *
 * Created by Chalat Chansima on 6/10/18.
 *
 */
class VerticalViewPager(context: Context, attrs: AttributeSet?)
    : ViewPager(context, attrs) {

    init {
        // The majority of the magic happens here
        setPageTransformer(true, VerticalPageTransformer())
        // The easiest way to get rid of the over scroll drawing that happens on the left and right
        overScrollMode = View.OVER_SCROLL_NEVER
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        val intercepted = super.onInterceptTouchEvent(swapXY(ev))
        swapXY(ev) // return touch coordinates to original reference frame for any child views
        return intercepted
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return super.onTouchEvent(swapXY(ev))
    }

    private inner class VerticalPageTransformer : ViewPager.PageTransformer {

        override fun transformPage(view: View, position: Float) {
            when {
                position < -1 -> // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    view.alpha = 0F
                position <= 1 -> { // [-1,1]
                    view.alpha = 1F
                    // Counteract the default slide transition
                    view.translationX = view.width * -position
                    //set Y position to swipe in from top
                    val yPosition = position * view.height
                    view.translationY = yPosition
                }
                else -> // (1,+Infinity]
                    // This page is way off-screen to the right.
                    view.alpha = 0F
            }
        }
    }

    private fun swapXY(ev: MotionEvent): MotionEvent {
        val width = width.toFloat()
        val height = height.toFloat()
        val newX = ev.y / height * width
        val newY = ev.x / width * height
        ev.setLocation(newX, newY)
        return ev
    }

}
