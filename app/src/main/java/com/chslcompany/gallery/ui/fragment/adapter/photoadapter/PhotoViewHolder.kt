package com.chslcompany.gallery.ui.fragment.adapter.photoadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chslcompany.core.model.PhotoDomain
import com.chslcompany.gallery.R
import com.chslcompany.gallery.databinding.ItemPhotoBinding

class PhotoViewHolder(
    itemPhotoBinding: ItemPhotoBinding,
    private val photoCallback: (photo : PhotoDomain) -> Unit
) : RecyclerView.ViewHolder(itemPhotoBinding.root) {

    private val image = itemPhotoBinding.image
    private val name = itemPhotoBinding.name

    fun bind(photoDomain: PhotoDomain){
        Glide.with(itemView.context)
            .load(photoDomain.srcDomain.original)
            .centerCrop()
            .fallback(R.drawable.baseline_broken)
            .into(image)

        name.text = photoDomain.photographer

        itemView.setOnClickListener {
            photoCallback.invoke(photoDomain)
        }
    }

    companion object{
        fun create(
            parent : ViewGroup, photoCallback: (photo : PhotoDomain) -> Unit
        ) : PhotoViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemPhotoBinding = ItemPhotoBinding.inflate(inflater, parent, false)
            return PhotoViewHolder(itemPhotoBinding, photoCallback)
        }
    }

}