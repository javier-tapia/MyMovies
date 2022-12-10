package com.example.mymovies.model

import com.example.mymovies.model.MediaItem.Type.PHOTO
import com.example.mymovies.model.MediaItem.Type.VIDEO

data class MediaItem(
    val id: Int,
    val title: String,
    val thumb: String,
    val type: Type
) {
    enum class Type {
        PHOTO,
        VIDEO
    }
}

fun getMedia() = (1..10).map {
    MediaItem(
        id = it,
        title = "Title $it",
        thumb = "https://loremflickr.com/400/400/dog?lock=$it",
        type = if (it % 3 == 0) VIDEO else PHOTO
    )
}
