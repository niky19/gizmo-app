package com.example.example

import com.google.gson.annotations.SerializedName


data class Meta (

  @SerializedName("pagination" ) var pagination : Pagination? = Pagination()

)