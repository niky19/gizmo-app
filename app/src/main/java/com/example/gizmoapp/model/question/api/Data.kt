package com.example.example

import com.google.gson.annotations.SerializedName


data class Data (

  @SerializedName("id"         ) var id         : Int?        = null,
  @SerializedName("attributes" ) var attributes : Attributes? = Attributes()

)