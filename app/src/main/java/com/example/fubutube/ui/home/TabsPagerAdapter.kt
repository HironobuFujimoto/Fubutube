package com.example.fubutube.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fubutube.ui.home.tab.OceanFragment
import com.example.fubutube.ui.home.tab.TigerFragment

class TabsPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2 // タブの数

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OceanFragment()
            1 -> TigerFragment()
            else -> throw IllegalArgumentException("エラー")
        }
    }
}