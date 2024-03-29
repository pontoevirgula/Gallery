package com.chslcompany.gallery.ui.fragment.gallery

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.work.*
import com.chslcompany.core.model.PhotoDomain
import com.chslcompany.gallery.databinding.FragmentGalleryBinding
import com.chslcompany.gallery.framework.workmanager.WallpaperWorkManager
import com.chslcompany.gallery.util.CustomDialog
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit
import javax.inject.Inject

private const val WORK_NAME = "WALLPAPER_WORK_MANAGER"

@AndroidEntryPoint
class GalleryFragment : Fragment() {
    private lateinit var binding : FragmentGalleryBinding
    private val viewModel : GalleryViewModel by viewModels()

    private val galleryAdapter : GalleryAdapter by lazy {
        GalleryAdapter(::detail, ::delete)
    }

    @Inject
    lateinit var workManager: WorkManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAll()
        backButton()
        startWorkManager(workManager)
        binding.switchId.setOnCheckedChangeListener { _, isChecked ->
            if (!isChecked) {
                cancelWorkManager(workManager)
            }
        }
    }

    private fun setupRecyclerView() {
        val gridLayoutManager = GridLayoutManager(requireContext(), 3)
        with(binding.galleryRv){
            layoutManager = gridLayoutManager
            setHasFixedSize(true)
            adapter = galleryAdapter
        }
    }

    private fun getAll() {
        viewModel.state.observe(viewLifecycleOwner) { uiState ->
            when(uiState) {
                is GalleryViewModel.UiState.ShowGallery -> {
                    setupRecyclerView()
                    galleryAdapter.submitList(uiState.photos)
                }
                is GalleryViewModel.UiState.ErrorGallery -> {
                    snackBarMessage(ERROR_MESSAGE)
                }
                is GalleryViewModel.UiState.EmptyGallery -> {
                    galleryAdapter.submitList(emptyList())
                    snackBarMessage(EMPTY_LIST)
                }
            }
        }
    }

    private fun detail(photoDomain: PhotoDomain) {
        val data = arrayOf(photoDomain.srcDomain?.original, photoDomain.description)
        findNavController().navigate(GalleryFragmentDirections.actionGalleryFragmentToDownloadFragment(data))
    }

    private fun delete(photoDomain: PhotoDomain){
        val dialog = CustomDialog(photoDomain) {
            viewModel.deleteGallery(photoDomain)
        }
        dialog.show(childFragmentManager, DELETE_DIALOG)
    }

    private fun backButton() {
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun snackBarMessage(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT)
            .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
            .show()
    }

    @SuppressLint("InvalidPeriodicWorkRequestInterval")
    private fun startWorkManager(workManager: WorkManager) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val wallpaperWorker =
            PeriodicWorkRequest.Builder(WallpaperWorkManager::class.java, 1, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        workManager.enqueueUniquePeriodicWork(
            WORK_NAME,
            ExistingPeriodicWorkPolicy.UPDATE,
            wallpaperWorker
        )
    }

    private fun cancelWorkManager(workManager: WorkManager) {
        workManager.cancelUniqueWork(WORK_NAME)
    }

    companion object {
        private const val DELETE_DIALOG = "Delete Dialog"
        private const val EMPTY_LIST = "lista de fotos vazia"
        private const val ERROR_MESSAGE = "Ops! Ocorreu um erro inesperado em nosso banco de dados"
    }

}