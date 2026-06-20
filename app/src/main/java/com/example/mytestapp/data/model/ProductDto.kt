package com.example.mytestapp.data.model

import com.example.mytestapp.domain.model.Product


data class ProductDto(
    val id: String, val title: String,
    val price: String, val description: String,
    val category: String
)

data class ProductResponse(val productList: List<ProductDto>)

fun ProductDto.toProduct(): Product {
    return Product(
        id = id,
        title = title,
        price = price
    )
}








