package com.example.gizmoapp.model.question.api

import com.google.gson.annotations.SerializedName


data class Attributes (

  @SerializedName("statement"   ) var statement   : String? = null,
  @SerializedName("answer"      ) var answer      : Int?    = null,
  @SerializedName("createdAt"   ) var createdAt   : String? = null,
  @SerializedName("updatedAt"   ) var updatedAt   : String? = null,
  @SerializedName("publishedAt" ) var publishedAt : String? = null,
  @SerializedName("option1"     ) var option1     : String? = null,
  @SerializedName("option2"     ) var option2     : String? = null,
  @SerializedName("option3"     ) var option3     : String? = null,
  @SerializedName("option4"     ) var option4     : String? = null

)