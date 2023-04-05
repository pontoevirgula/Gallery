package com.chslcompany.gallery.ui.extensions

import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chslcompany.core.usecase.base.ResultStatus
import com.chslcompany.gallery.R
import kotlinx.coroutines.flow.Flow

fun ImageView.loadBlurredImageWithPlaceholder(
    imageUrl: String?,
    placeholderColor: String?,
    placeholderRadius: Float = 25f,
    size: Int = 100
) {
    val placeholderColorInt = Color.parseColor(placeholderColor)
    val blurMaskFilter = BlurMaskFilter(placeholderRadius, BlurMaskFilter.Blur.NORMAL)
    val paint = Paint()
    paint.maskFilter = blurMaskFilter
    paint.color = placeholderColorInt
    val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    canvas.drawRect(0f, 0f, size.toFloat(), size.toFloat(), paint)

    val placeholderDrawable = BitmapDrawable(resources, bitmap)

    Glide.with(this)
        .load(imageUrl)
        .centerCrop()
        .placeholder(placeholderDrawable)
        .fallback(R.drawable.baseline_broken)
        .into(this)
}

suspend fun <T> Flow<ResultStatus<T>>.watchStatus(
    loading: suspend () -> Unit = {},
    success: suspend (data: T) -> Unit,
    error: suspend (throwable: Throwable) -> Unit
) {
    collect { status ->
        when(status) {
            ResultStatus.Loading -> loading.invoke()
            is ResultStatus.Success -> success.invoke(status.data)
            is ResultStatus.Error -> error.invoke(status.throwable)
        }
    }
}