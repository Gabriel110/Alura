package br.com.gabriel.shop.application.dto

import br.com.gabriel.shop.domain.model.Shop
import java.time.LocalDate

data class ShopResponse(
    val identifier: String,
    val status: Shop.Status,
    val dateShop: LocalDate,
    val itens: List<ShopItemResponse>
)