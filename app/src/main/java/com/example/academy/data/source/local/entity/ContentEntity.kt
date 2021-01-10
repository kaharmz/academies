package com.example.academy.data.source.local.entity

import androidx.room.ColumnInfo


class ContentEntity(

        @ColumnInfo(name = "content")
        var content: String?
)