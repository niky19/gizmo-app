package com.example.example

import com.google.gson.annotations.SerializedName


data class QuestionResponse (

  @SerializedName("data" ) var data : ArrayList<Data> = arrayListOf(),
  @SerializedName("meta" ) var meta : Meta?           = Meta()

)