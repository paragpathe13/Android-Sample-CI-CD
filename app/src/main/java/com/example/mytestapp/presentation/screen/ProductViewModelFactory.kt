package com.example.mytestapp.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mytestapp.domain.usecase.GetProductsUseCase

class ProductViewModelFactory(
    val useCase: GetProductsUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductViewModel(useCase) as T
    }
}