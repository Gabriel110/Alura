package br.com.gabriel.shop.application.dto

data class ShopItemRequest(
    val productIdentifier: String,
    val amount: Int,
    val price: Float
)