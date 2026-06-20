package com.example.mytestapp.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytestapp.domain.model.Product
import com.example.mytestapp.domain.usecase.GetProductsUseCase
import com.example.mytestapp.presentation.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _state =
        MutableStateFlow<UiState<List<Product>>>(
            UiState.Loading
        )

    val sate: StateFlow<UiState<List<Product>>> =
        _state

    init {
        fetchProductList()
    }

    fun fetchProductList() {

        viewModelScope.launch {

            _state.value = UiState.Loading

            try {

                val products =
                    getProductsUseCase()

                _state.value =
                    if (products.isEmpty()) {
                        UiState.Empty
                    } else {
                        UiState.Success(products)
                    }

            } catch (e: Exception) {
                _state.value =
                    UiState.Error(
                        e.message
                            ?: "Something went wrong"
                    )
            }
        }
    }
}












