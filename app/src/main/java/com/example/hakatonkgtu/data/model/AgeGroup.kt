package com.example.hakatonkgtu.data.model


import com.google.gson.annotations.SerializedName

data class AgeGroup(
    @SerializedName("description")
    val description: String,
    @SerializedName("fill")
    val fill: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("trend")
    val trend: String,
    @SerializedName("value")
    val value: Int
)