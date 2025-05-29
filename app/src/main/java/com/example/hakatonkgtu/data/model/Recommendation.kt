package com.example.hakatonkgtu.data.model


import com.google.gson.annotations.SerializedName

data class Recommendation(
    @SerializedName("analysisBasis")
    val analysisBasis: String,
    @SerializedName("count")
    val count: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("priority")
    val priority: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("unit")
    val unit: String
)