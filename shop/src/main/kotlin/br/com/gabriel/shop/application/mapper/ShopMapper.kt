package br.com.gabriel.shop.application.mapper

import br.com.gabriel.shop.application.dto.ShopItemRequest
import br.com.gabriel.shop.application.dto.ShopItemResponse
import br.com.gabriel.shop.application.dto.ShopResponse
import br.com.gabriel.shop.application.dto.ShopResquest
import br.com.gabriel.shop.domain.model.Shop
import br.com.gabriel.shop.domain.model.ShopItem
import java.time.LocalDate
import java.util.UUID

fun ShopResquest.toModel() = Shop(
    identifier = UUID.randomUUID().toString(),
    status = "PENDING",
    dateShop = LocalDate.now(),
    items = emptyList()
)

fun ShopResquest.toShopItesn(shop: Shop) = this.items.map {
    ShopItem(
        productIdentifier = it.productIdentifier,
        amount = it.amount,
        price = it.price,
        shop = shop
    )
}


fun ShopItem.toRequest() = ShopItemRequest(
    price = this.price,
    amount = this.amount,
    productIdentifier = this.productIdentifier,
)

fun ShopItem.toResponse() = ShopItemResponse(
    amount = this.amount,
    price = this.price,
    productIdentifier = this.productIdentifier,
    id = this.id!!,
)

fun Shop.toRequest() = ShopResquest(
    items = this.items.map { it.toRequest() }
)

fun Shop.toResponse() = ShopResponse(
    identifier = this.identifier,
    status = this.status,
    dateShop = this.dateShop,
    itens = this.items.map { it.toResponse()}
)

fun Shop.toResponseV2(shopItem: List<ShopItem>) = ShopResponse(
    identifier = this.identifier,
    status = this.status,
    dateShop = this.dateShop,
    itens = shopItem.map {
        ShopItemResponse(
            productIdentifier = it.productIdentifier,
            amount = it.amount,
            price = it.price,
            id = it.id!!
        )
    }
)