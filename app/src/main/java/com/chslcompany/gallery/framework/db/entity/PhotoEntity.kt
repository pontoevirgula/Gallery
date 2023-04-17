package com.chslcompany.gallery.framework.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chslcompany.core.data.DbConstants
import com.chslcompany.core.model.PhotoDomain
import com.chslcompany.core.model.SrcDomain

@Entity(tableName = DbConstants.APP_TABLE_NAME)
data class PhotoEntity(
    @PrimaryKey
    var id : Int,
    var photo : String,
    var smallPhoto : String,
    var photographer : String,
    var avgColor : String
)

fun List<PhotoEntity>.toPhotoDomain() = map{
        PhotoDomain(
            id = it.id,
            photographer = it.photographer,
            avgColor = it.avgColor,
            srcDomain = SrcDomain(
                original = it.photo,
                small = it.smallPhoto
            )
        )
    }



