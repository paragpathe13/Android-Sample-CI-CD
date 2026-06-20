package com.example.mytestapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mytestapp.data.api.RetrofitInstance
import com.example.mytestapp.data.repository.ProductRepositoryImpl
import com.example.mytestapp.domain.usecase.GetProductsUseCase
import com.example.mytestapp.presentation.screen.ProductListScreen
import com.example.mytestapp.presentation.screen.ProductViewModel
import com.example.mytestapp.presentation.screen.ProductViewModelFactory
import com.example.mytestapp.ui.theme.MyTestAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            MyTestAppTheme {
                val api = RetrofitInstance.api
                val repository = ProductRepositoryImpl(api)
                val useCase = GetProductsUseCase(repository)
                val viewModel: ProductViewModel = viewModel(
                    factory = ProductViewModelFactory(useCase)
                )

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(title = { Text("Product List") })
                    }) { paddingValues ->

                    //Spacer(modifier = Modifier.padding(paddingValues))
                    ProductListScreen(
                        viewModel = viewModel, modifier =
                            Modifier.padding(paddingValues)
                    )

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyTestAppTheme {
        Greeting("Android")
    }
}