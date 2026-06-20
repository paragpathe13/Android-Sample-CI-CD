package com.example.mytestapp.domain.repository

import com.example.mytestapp.domain.model.Product

interface ProductRepository {
    suspend fun getProductList(): List<Product>
}
