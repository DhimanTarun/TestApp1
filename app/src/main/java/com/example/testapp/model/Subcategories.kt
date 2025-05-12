package com.example.testapp.model

import com.example.testapp.model.Items
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Subcategories (

  @SerialName("id"         ) var id         : Int?             = null,
  @SerialName("name"       ) var name       : String?          = null,
  @SerialName("isCategory" ) var isCategory : Int?             = null,
  @SerialName("items"      ) var items      : ArrayList<Items> = arrayListOf()

)