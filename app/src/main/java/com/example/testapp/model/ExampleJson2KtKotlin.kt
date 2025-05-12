package com.example.testapp.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class ExampleJson2KtKotlin (

  @SerialName("categories" ) var categories : ArrayList<Categories> = arrayListOf()

)