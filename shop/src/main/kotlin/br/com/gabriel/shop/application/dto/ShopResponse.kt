package br.com.gabriel.shop.application.dto

import java.time.LocalDate

data class ShopResponse(
    val identifier: String,
    val status: String,
    val dateShop: LocalDate,
    val itens: List<ShopItemResponse>
)