package com.example.mytestapp.data.repository

import com.example.mytestapp.data.api.ProductApi
import com.example.mytestapp.data.model.toProduct
import com.example.mytestapp.domain.model.Product
import com.example.mytestapp.domain.repository.ProductRepository

class ProductRepositoryImpl(val api: ProductApi) : ProductRepository {
    override suspend fun getProductList(): List<Product> {
        return api.getProducts().map {
            it.toProduct()
        }
    }

}