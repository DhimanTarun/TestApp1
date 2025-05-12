package com.example.testapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testapp.model.Categories
import com.example.testapp.model.Subcategories
import com.example.testapp.ui.theme.TestAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
         val viewModel by viewModels<MainViewModel>()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Greeting(viewModel)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestAppTheme {
        Greeting(MainViewModel())
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(viewModel: MainViewModel) {
    TestAppTheme {

        val currentItems by viewModel.currentItems
        val navigationStack by viewModel.navigationStack

        if (currentItems.isEmpty() && navigationStack.isEmpty()) {
            viewModel.loadInitialData()
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(navigationStack.joinToString(" > ")) },
                    navigationIcon = {
                        if (navigationStack.isNotEmpty()) {
                            IconButton(onClick = { viewModel.navigateBack() }) {
                                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                            }
                        }
                    }
                )
            }
        ) { paddingValues ->
            ItemListScreen(
                items = currentItems,
                onItemClick = { viewModel.navigateTo(it) },
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}

@Composable
fun ItemListScreen(items: List<Categories>, onItemClick: (Categories) -> Unit, modifier: Modifier = Modifier) {
    if (items.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No items to display.")
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // You can adjust the number of columns
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
        ) {
            items(items) { item ->
                ItemCard(item = item, onItemClick = onItemClick)
            }
        }
    }
}

@Composable
fun ItemCard(item: Categories, onItemClick: (Categories) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(item) },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = item.name.toString(), style = MaterialTheme.typography.headlineSmall)
            if (item.subcategories.isNotEmpty()) {
                Text(text = "${item.subcategories.size} Subcategories", style = MaterialTheme.typography.bodyMedium)
            } else if (item.subcategories.isNotEmpty()) {
                Text(text = "${item.subcategories.size} Items", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

