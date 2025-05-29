package com.example.hakatonkgtu.data.model


import com.google.gson.annotations.SerializedName

data class DatasItem(
    @SerializedName("ageGroups")
    val ageGroups: List<AgeGroup>,
    @SerializedName("capacities")
    val capacities: Capacities,
    @SerializedName("kindergartens")
    val kindergartenns: List<Kindergartenn>,
    @SerializedName("recommendations")
    val recommendations: List<Recommendation>,
    @SerializedName("schools")
    val schoolls: List<Schooll>,
    @SerializedName("year")
    val year: String
)