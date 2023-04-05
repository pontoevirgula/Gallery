package com.chslcompany.gallery.ui.fragment.adapter.photoadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chslcompany.core.model.PhotoDomain
import com.chslcompany.gallery.databinding.ItemPhotoBinding
import com.chslcompany.gallery.ui.extensions.loadBlurredImageWithPlaceholder

class PhotoViewHolder(
    itemPhotoBinding: ItemPhotoBinding,
    private val clickCallback: (photo: PhotoDomain) -> Unit,
    private val longClickCallback: (photo: PhotoDomain) -> Unit
) : RecyclerView.ViewHolder(itemPhotoBinding.root) {

    private val image = itemPhotoBinding.image
    private val name = itemPhotoBinding.name

    fun bind(photoDomain: PhotoDomain){
        image.loadBlurredImageWithPlaceholder(
            imageUrl = photoDomain.srcDomain?.original,
            placeholderColor = photoDomain.avgColor,
        )

        name.text = photoDomain.photographer

        itemView.setOnClickListener {
            clickCallback.invoke(photoDomain)
        }

        itemView.setOnLongClickListener {
            longClickCallback.invoke(photoDomain)
            return@setOnLongClickListener true
        }
    }

    companion object{
        fun create(
            parent: ViewGroup,
            clickCallback: (photo: PhotoDomain) -> Unit,
            longClickCallback: (photo: PhotoDomain) -> Unit
        ) : PhotoViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemPhotoBinding = ItemPhotoBinding.inflate(inflater, parent, false)
            return PhotoViewHolder(itemPhotoBinding, clickCallback, longClickCallback)
        }
    }

}