package com.chslcompany.gallery.framework.workmanager

import android.app.WallpaperManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.chslcompany.gallery.framework.db.dao.WallPaperDao
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL
import javax.inject.Inject

@HiltWorker
class WallpaperWorkManager @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val wallpaperManager: WallpaperManager
) : CoroutineWorker(context, workerParameters) {

    @Inject
    lateinit var dao: WallPaperDao

    @RequiresApi(Build.VERSION_CODES.N)
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val photo = dao.getRandomWallpaper()
            converterUrlToBitmap(photo.photo)
            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun converterUrlToBitmap(url : String) {
        try {
            val bitmap = BitmapFactory.decodeStream(URL(url).openStream())
            wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_SYSTEM)
        } catch (e : Exception) {
            e.printStackTrace()
        }
    }
}