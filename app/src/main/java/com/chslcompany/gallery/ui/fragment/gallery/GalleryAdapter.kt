package com.chslcompany.gallery.ui.fragment.gallery

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.chslcompany.core.model.PhotoDomain
import com.chslcompany.gallery.ui.fragment.adapter.photoadapter.PhotoViewHolder

class GalleryAdapter(
    private val clickCallback: (photo: PhotoDomain) -> Unit,
    private val longClickCallback: (photo: PhotoDomain) -> Unit
) : ListAdapter<PhotoDomain, PhotoViewHolder>(differCallback) {

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder =
        PhotoViewHolder.create(parent, clickCallback, longClickCallback)

    companion object {
        private val differCallback = object : DiffUtil.ItemCallback<PhotoDomain>() {
            override fun areItemsTheSame(oldItem: PhotoDomain, newItem: PhotoDomain): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: PhotoDomain, newItem: PhotoDomain): Boolean {
                return oldItem == newItem
            }

        }
    }

}