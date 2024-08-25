package br.com.gabriel.shop.application.dto

import java.time.LocalDate

data class ShopResquest(
    val identifier: String,
    val status: String,
    val dateShop: LocalDate,
    val items: List<ShopItemRequest>
)