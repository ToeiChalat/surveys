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
class  MainViewPagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {

    private var dataList = ArrayList<Survey>()

    override fun getItem(position: Int): Fragment {
        return SurveyMainFragment.newInstance(dataList[position])
    }

    override fun getCount(): Int {
        return dataList.size
    }

    fun replaceData(surveyList: List<Survey>) {
        dataList = ArrayList(surveyList)
        notifyDataSetChanged()
    }

}