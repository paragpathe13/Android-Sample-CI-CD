package com.example.mytestapp.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mytestapp.domain.model.Product
import com.example.mytestapp.presentation.UiState

@Composable
fun ProductListScreen(
    viewModel: ProductViewModel,
    modifier: Modifier = Modifier
) {
    val state by viewModel.sate.collectAsStateWithLifecycle()

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        when (state) {

            is UiState.Loading -> {
                CircularProgressIndicator()
            }

            is UiState.Success -> {
                val products = (state as UiState.Success<List<Product>>).data
                ProductList(
                    products = products
                )
            }

            is UiState.Error -> {
                Text(text = (state as UiState.Error).errorMessage)
            }

            else -> {
                Text(text = "test")
            }
        }
    }
}

@Composable
fun ProductList(
    products: List<Product>
) {
    LazyColumn {

        items(
            items = products,
            key = { product ->
                product.id
            }
        ) { product ->

            ProductItem(product)
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Card(modifier = Modifier.padding(10.dp)) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = product.title)
            Text(text = product.price)
        }
    }
}