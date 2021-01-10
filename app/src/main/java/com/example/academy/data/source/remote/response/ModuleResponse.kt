package com.example.academy.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
class ModuleResponse(
    var moduleId: String,
    var courseId: String,
    var title: String,
    var position: Int
) : Parcelable