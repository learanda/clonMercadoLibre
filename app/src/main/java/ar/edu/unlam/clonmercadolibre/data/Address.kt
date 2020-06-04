package ar.edu.unlam.clonmercadolibre.data

import com.google.gson.annotations.SerializedName

data class Address (

    @SerializedName("state_id") val state_id : String,
    @SerializedName("state_name") val state_name : String,
    @SerializedName("city_id") val city_id : String,
    @SerializedName("city_name") val city_name : String

)