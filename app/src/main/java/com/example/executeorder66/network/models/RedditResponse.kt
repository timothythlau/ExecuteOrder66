package com.example.executeorder66.network.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class RedditResponse(
    val data: MainDataResponse?
)

data class MainDataResponse(
    val children: List<ChildResponse>?
)

data class ChildResponse(
    val data: ChildDataResponse?
)

@Parcelize
data class ChildDataResponse(
    val title: String?,
    val selftext: String?,
    val thumbnail: String?
) : Parcelable