package com.example.lapstore.views

data  class Product(
    val name: String,
    val Describe:String,
    var discountPrice: Int,
    var originalPrice: Int,
    val imageRes: Int,
    var quantity: Int,
    var gift:String
)