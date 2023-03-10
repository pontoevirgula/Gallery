package com.chslcompany.gallery.ui.fragment.pageadapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPageAdapter(
    container: FragmentActivity, private val fragmentList: List<Fragment>
) : FragmentStateAdapter(container) {

    override fun getItemCount() = fragmentList.size

    override fun createFragment(position: Int) = fragmentList[position]
}