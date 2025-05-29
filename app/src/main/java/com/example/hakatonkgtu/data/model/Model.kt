package com.example.hakatonkgtu.data.model
import com.google.gson.annotations.SerializedName

interface EducationalInstitution {
    val id: Int
    val name: String
    val location: String
    val capacity: Int
    val current_load: Int
}

data class MapDataResponse(
    val districts: List<District>,
    val schoolls: List<Schooll>,
    val kindergartenns: List<Kindergartenn>
)

data class District(
    val id: Int,
    val name: String,
    val geometry: String
)

data class Schooll(
    override val id: Int,
    override val name: String,
    override val location: String,
    override val capacity: Int,
    override val current_load: Int
) : EducationalInstitution

data class Kindergartenn(
    override val id: Int,
    override val name: String,
    override val location: String,
    override val capacity: Int,
    override val current_load: Int
) : EducationalInstitution

data class GeoPointData(
    @SerializedName("type") val type: String,
    @SerializedName("coordinates") val coordinates: List<Double>
)