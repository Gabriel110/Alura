package br.com.gabriel.shop.application.mapper

import br.com.gabriel.shop.application.dto.ShopItemRequest
import br.com.gabriel.shop.application.dto.ShopItemResponse
import br.com.gabriel.shop.application.dto.ShopResponse
import br.com.gabriel.shop.application.dto.ShopResquest
import br.com.gabriel.shop.domain.model.Shop
import br.com.gabriel.shop.domain.model.ShopItem

fun ShopItemRequest.toModel() = ShopItem(
    shop = this.shop,
    price = this.price,
    productIdentifier = this.productIdentifier,
    amount = this.amount
)

fun ShopResquest.toModel() = Shop(
    items = this.items.map { it.toModel() },
    status = this.status,
    dateShop = this.dateShop,
    identifier = this.identifier
)

fun ShopItem.toRequest() = ShopItemRequest(
    price = this.price,
    amount = this.amount,
    productIdentifier = this.productIdentifier,
    shop = this.shop
)

fun ShopItem.toResponse() = ShopItemResponse(
    amount = this.amount,
    price = this.price,
    productIdentifier = this.productIdentifier,
    id = this.id!!,
)

fun Shop.toRequest() = ShopResquest(
    identifier = this.identifier,
    status = this.status,
    dateShop = this.dateShop,
    items = this.items.map { it.toRequest() }
)

fun Shop.toResponse() = ShopResponse(
    identifier = this.identifier,
    status = this.status,
    dateShop = this.dateShop,
    itens = this.items.map { it.toResponse() }
)