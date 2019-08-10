package com.dnd.killcaffeine.main

import android.app.Activity
import android.graphics.drawable.Drawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.main.home.MainHomeFragment
import com.dnd.killcaffeine.main.settings.MainSettingsFragment
import com.dnd.killcaffeine.main.statistics.MainStatisticsFragment

/*
 * Created by Lee Oh Hyoung on 2019-08-10..
 */
class MainViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    private val fragmentList = listOf<Fragment>(
        MainHomeFragment.newInstance(),
        MainStatisticsFragment.newInstance(),
        MainSettingsFragment.newInstance()
    )

    override fun getItemCount(): Int = fragmentList.size
    override fun createFragment(position: Int): Fragment = fragmentList[position]

    companion object {
        private val tabIconList = listOf(
            R.drawable.icon_tab_home_selector,
            R.drawable.icon_tab_statistics_selector,
            R.drawable.icon_tab_settings_selector
        )

        fun getTabIcon(position: Int): Int = tabIconList[position]
    }
}