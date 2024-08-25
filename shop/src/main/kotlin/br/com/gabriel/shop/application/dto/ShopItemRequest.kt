package br.com.gabriel.shop.application.dto

import br.com.gabriel.shop.domain.model.Shop

data class ShopItemRequest(
    val productIdentifier: String,
    val amount: Int,
    val price: Float,
    val shop: Shop
)