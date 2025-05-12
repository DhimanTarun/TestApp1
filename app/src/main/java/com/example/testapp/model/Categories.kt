package com.example.testapp.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Categories (

  @SerialName("id"              ) var id              : Int?                     = null,
  @SerialName("name"            ) var name            : String?                  = null,
  @SerialName("isSuperCategory" ) var isSuperCategory : Int?                     = null,
  @SerialName("subcategories"   ) var subcategories   : ArrayList<Subcategories> = arrayListOf()

)