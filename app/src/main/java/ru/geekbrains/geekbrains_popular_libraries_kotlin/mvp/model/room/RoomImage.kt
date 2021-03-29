package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomImage(
    @PrimaryKey var url: String,
    var localPath: String
)

