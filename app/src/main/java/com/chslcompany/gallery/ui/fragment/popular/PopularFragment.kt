package com.chslcompany.gallery.ui.fragment.popular

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.chslcompany.core.model.PhotoDomain
import com.chslcompany.gallery.R
import com.chslcompany.gallery.databinding.FragmentPopularBinding
import com.chslcompany.gallery.ui.fragment.adapter.photoadapter.PhotoAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularFragment : Fragment() {
    private lateinit var binding : FragmentPopularBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPopularBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        val photoAdapter = PhotoAdapter(::detail)
        val gridLayoutManager = GridLayoutManager(requireContext(), 3)
        with(binding.recyclerView){
            scrollToPosition(0)
            layoutManager = gridLayoutManager
            setHasFixedSize(true)
            adapter = photoAdapter
        }
    }

    private fun detail(photoDomain: PhotoDomain){

    }

}