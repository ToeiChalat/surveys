package com.chalat.surveys.feature.app

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.chalat.surveys.feature.survey.data.Survey
import com.chalat.surveys.feature.survey.main.SurveyMainFragment

/**
 *
 * Created by Chalat Chansima on 6/10/18.
 *
 */
class MainViewPagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {

    interface LastPageListener {
        fun onLastPage()
    }

    private var dataList = ArrayList<Survey>()
    private var lastPageListener: LastPageListener? = null

    override fun getItem(position: Int): Fragment {
        if (position == dataList.size - 1) lastPageListener?.onLastPage()
        return SurveyMainFragment.newInstance(dataList[position])
    }

    override fun getCount(): Int {
        return dataList.size
    }

    fun replaceData(surveyList: List<Survey>) {
        dataList = ArrayList(surveyList)
        notifyDataSetChanged()
    }

    fun appendData(surveyList: List<Survey>) {
        dataList.addAll(surveyList)
        notifyDataSetChanged()
    }

    fun setOnLastPageListener(lastPageListener: LastPageListener) {
        this.lastPageListener = lastPageListener
    }

}