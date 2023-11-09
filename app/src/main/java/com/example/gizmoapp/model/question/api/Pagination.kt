package com.example.example

import com.google.gson.annotations.SerializedName


data class Pagination (

  @SerializedName("page"      ) var page      : Int? = null,
  @SerializedName("pageSize"  ) var pageSize  : Int? = null,
  @SerializedName("pageCount" ) var pageCount : Int? = null,
  @SerializedName("total"     ) var total     : Int? = null

)