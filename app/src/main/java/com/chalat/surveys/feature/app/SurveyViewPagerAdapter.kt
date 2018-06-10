package com.chalat.surveys.feature.app

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 *
 * Created by Chalat Chansima on 6/10/18.
 *
 */
class SurveyViewPagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {

//    private val dataList = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        return SurveyFragment.newInstance(position.toString())
    }

    override fun getCount(): Int {
//        return dataList.size
        return 5
    }

}