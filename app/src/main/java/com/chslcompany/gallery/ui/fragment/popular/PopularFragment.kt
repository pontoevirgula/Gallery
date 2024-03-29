package com.chslcompany.gallery.ui.fragment.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.chslcompany.core.model.PhotoDomain
import com.chslcompany.gallery.R
import com.chslcompany.gallery.databinding.FragmentPopularBinding
import com.chslcompany.gallery.ui.fragment.adapter.photoadapter.PhotoAdapter
import com.chslcompany.gallery.ui.fragment.main.MainFragmentDirections
import com.chslcompany.gallery.ui.fragment.popular.viewmodel.PopularViewModel
import com.chslcompany.gallery.util.animationCancel
import com.chslcompany.gallery.util.pulseAnimation
import com.google.android.material.snackbar.BaseTransientBottomBar.ANIMATION_MODE_SLIDE
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PopularFragment : Fragment() {
    private lateinit var binding : FragmentPopularBinding
    private val viewModel : PopularViewModel by viewModels()
    private val photoAdapter by lazy { PhotoAdapter(::detail, ::insertData) }

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
        observerLoadState()
        observerFavoriteUiState()
        fetchWallPapers()
    }

    private fun initAdapter() {
        val gridLayoutManager = GridLayoutManager(requireContext(), 3)
        with(binding.recyclerView){
            scrollToPosition(0)
            layoutManager = gridLayoutManager
            setHasFixedSize(true)
            adapter = photoAdapter
        }
    }

    private fun observerFavoriteUiState() {
        viewModel.favoriteUiState.observe(viewLifecycleOwner) { favoriteUiState ->
            when (favoriteUiState) {
                PopularViewModel.FavoriteUiStatus.Loading -> ONE_ALEATORY
                is PopularViewModel.FavoriteUiStatus.FavoritePhoto -> {
                    if (favoriteUiState.saved) favoriteItemMessage(getString(R.string.item_save))
                    else favoriteItemMessage(getString(R.string.error_to_save))
                }
            }
        }
    }

    private fun favoriteItemMessage(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT)
            .setAnimationMode(ANIMATION_MODE_SLIDE)
            .show()
    }

    private fun fetchWallPapers() =
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.popularWallPapers().collect { pagingData ->
                    photoAdapter.submitData(pagingData)
                }
            }
        }

    private fun observerLoadState() {
        lifecycleScope.launch {
            photoAdapter.loadStateFlow.collectLatest { loadState ->
                when(loadState.refresh) {
                    is LoadState.Loading -> {
                        binding.imagePulseAnimation.pulseAnimation()
                    }
                    is LoadState.NotLoading -> {
                        binding.imagePulseAnimation.animationCancel()
                    }
                    is LoadState.Error -> {
                       Toast.makeText(requireContext(),
                           getString(R.string.error_to_load_wallpapers), Toast.LENGTH_SHORT
                       ).show()
                    }
                }
            }
        }
    }

    private fun detail(photoDomain: PhotoDomain){
        val data = arrayOf(photoDomain.srcDomain?.original, photoDomain.description)
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToDownloadFragment(data))
    }

    private fun insertData(photoDomain: PhotoDomain) {
        viewModel.favoritePhoto(photoDomain)
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToGalleryFragment())
    }

    companion object {
        private const val ONE_ALEATORY = 1
    }
}