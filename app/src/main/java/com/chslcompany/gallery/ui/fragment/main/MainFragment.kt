package com.chslcompany.gallery.ui.fragment.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.chslcompany.gallery.R
import com.chslcompany.gallery.databinding.FragmentMainBinding
import com.chslcompany.gallery.ui.fragment.category.CategoriesFragment
import com.chslcompany.gallery.ui.fragment.collections.CollectionsFragment
import com.chslcompany.gallery.ui.fragment.pageadapter.ViewPageAdapter
import com.chslcompany.gallery.ui.fragment.popular.PopularFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

class MainFragment : Fragment() {

    private lateinit var binding : FragmentMainBinding
    private val tabTitle = listOf("Popular", "Collections", "Categories")
    private val fragments = listOf(PopularFragment(), CollectionsFragment(), CategoriesFragment())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initViewPager()
        initTabLayout()
    }

    private fun initTabLayout() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager){ tab, position->
            tab.text = tabTitle[position]
        }.attach()
    }

    private fun initToolbar() {
        binding.toolbar.title = getString(R.string.app_name)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
    }

    private fun initViewPager() {
        val pageAdapter = ViewPageAdapter(context as FragmentActivity, fragments)
        binding.viewPager.adapter = pageAdapter
    }
}