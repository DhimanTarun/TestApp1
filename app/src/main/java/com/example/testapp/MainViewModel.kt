package com.example.testapp

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.testapp.model.ExampleJson2KtKotlin
import com.example.testapp.model.Items
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.example.testapp.model.Categories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel :  ViewModel()  {
    val jsonData = "{\"categories\":[{\"id\":1,\"name\":\"Breakfast\",\"isSuperCategory\":1,\"subcategories\":[{\"id\":11,\"name\":\"Pizza\",\"isCategory\":1,\"items\":[{\"id\":111,\"name\":\"Large\",\"items\":[{\"name\":\"Lg Meat Lovers Pizza\",\"id\":201,\"price\":10},{\"name\":\"Lg Steak Lovers Pizza\",\"id\":202,\"price\":10},{\"name\":\"Pizza\",\"id\":11,\"isCategory\":1},{\"name\":\"Lg Ed'sPizza\",\"id\":203,\"price\":10},{\"name\":\"Lg Regular Pizza\",\"id\":204,\"price\":10}]}]},{\"id\":12,\"name\":\"Pasta\",\"items\":[{\"id\":121,\"name\":\"Fettuccine\",\"price\":10},{\"id\":122,\"name\":\"Spaghetti\",\"price\":10}]}]},{\"id\":2,\"name\":\"Pizza\",\"subcategories\":[{\"id\":21,\"name\":\"Cheese Pizza\",\"items\":[{\"id\":211,\"name\":\"Cheese Pizza\",\"price\":12}]}]},{\"id\":3,\"name\":\"Lunch\",\"subcategories\":[{\"id\":31,\"name\":\"Burger\",\"items\":[{\"id\":311,\"name\":\"Burger\",\"price\":8}]}]},{\"id\":4,\"name\":\"Dinner\",\"subcategories\":[]},{\"id\":5,\"name\":\"Drinks\",\"subcategories\":[{\"id\":51,\"name\":\"Cold Drinks\",\"items\":[{\"id\":511,\"name\":\"Coke\",\"price\":2}]}]},{\"id\":6,\"name\":\"Bread\",\"subcategories\":[]},{\"id\":7,\"name\":\"Burger\",\"subcategories\":[]},{\"id\":8,\"name\":\"Coffee\",\"subcategories\":[]}]}"

    val withUnknownKeys = Json { ignoreUnknownKeys = true }


    private val _currentItems = mutableStateOf<List<Categories>>(emptyList())
    val currentItems: State<List<Categories>> = _currentItems

    private val _navigationStack = mutableStateOf<List<String>>(emptyList())
    val navigationStack: State<List<String>> = _navigationStack

    fun loadInitialData() {
        viewModelScope.launch(Dispatchers.IO) {
            val initialItems =  withUnknownKeys.decodeFromString<ExampleJson2KtKotlin>(jsonData).categories

            initialItems.let {
                _currentItems.value = it
            }
        }
    }

    fun navigateTo(item: Categories) {
        _navigationStack.value = _navigationStack.value + item.name.toString()

           // Handle the final items display if needed

    }

    fun navigateBack() {
        if (_navigationStack.value.isNotEmpty()) {
            _navigationStack.value = _navigationStack.value.dropLast(1)
                      _currentItems.value = emptyList()
        }
    }
}