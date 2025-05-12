package com.example.testapp.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Items (

  @SerialName("id"    ) var id    : Int?             = null,
  @SerialName("name"  ) var name  : String?          = null,
  @SerialName("items" ) var items : ArrayList<Items> = arrayListOf()

)