package com.example.hakatonkgtu.data.model


import com.google.gson.annotations.SerializedName

data class School(
    @SerializedName("capacity")
    val capacity: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("position")
    val position: List<Double>,
    @SerializedName("queue")
    val queue: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("year_built")
    val yearBuilt: Int
)