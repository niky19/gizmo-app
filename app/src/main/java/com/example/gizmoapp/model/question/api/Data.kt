package com.example.example

import com.example.gizmoapp.model.question.api.Attributes
import com.google.gson.annotations.SerializedName


data class Data (

  @SerializedName("id"         ) var id         : Int?        = null,
  @SerializedName("attributes" ) var attributes : Attributes? = Attributes()

)