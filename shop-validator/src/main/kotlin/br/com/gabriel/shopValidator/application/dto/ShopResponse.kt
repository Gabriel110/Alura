package br.com.gabriel.shopValidator.application.dto

import br.com.gabriel.shopValidator.domain.enumerate.StatusShop
import java.time.LocalDate

data class ShopResponse(
    val identifier: String,
    val status: StatusShop,
    val dateShop: LocalDate,
    val itens: List<ShopItemResponse>
)