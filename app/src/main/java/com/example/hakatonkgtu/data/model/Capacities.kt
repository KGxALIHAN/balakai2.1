package com.example.hakatonkgtu.data.model


import com.google.gson.annotations.SerializedName

data class Capacities(
    @SerializedName("kindergartens")
    val kindergartens: Int,
    @SerializedName("schools")
    val schools: Int,
    @SerializedName("special_education")
    val specialEducation: Int
)