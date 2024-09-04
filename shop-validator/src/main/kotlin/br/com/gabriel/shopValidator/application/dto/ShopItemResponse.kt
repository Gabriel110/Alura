package br.com.gabriel.shopValidator.application.dto

data class ShopItemResponse(
    val productIdentifier: String,
    val amount: Int,
    val price: Float,
    val id: Long
)
