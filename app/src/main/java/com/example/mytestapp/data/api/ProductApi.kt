package com.example.mytestapp.data.api

import com.example.mytestapp.data.model.ProductDto
import retrofit2.http.GET


interface ProductApi {

    @GET("products")
    suspend fun getProducts(): List<ProductDto>
}