package com.example.mytestapp.domain.usecase

import com.example.mytestapp.domain.model.Product
import com.example.mytestapp.domain.repository.ProductRepository


class GetProductsUseCase(val repository: ProductRepository) {

    suspend operator fun invoke(): List<Product> {
        return repository.getProductList()
    }
}