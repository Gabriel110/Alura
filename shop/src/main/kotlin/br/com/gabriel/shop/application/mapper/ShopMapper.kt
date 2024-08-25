package br.com.gabriel.shop.application.mapper

import br.com.gabriel.shop.application.dto.ShopResponse
import br.com.gabriel.shop.application.dto.ShopResquest
import br.com.gabriel.shop.domain.model.Shop
import org.springframework.stereotype.Component

@Component
class ShopMapper {

    fun toDTO(shop: Shop): ShopResponse {
        return ShopResponse(
            identifier = shop.identifier,
            status = shop.status,
            dateShop = shop.dateShop
        )
    }

    fun toModel(shopDTO: ShopResquest): Shop {
        return Shop(
            identifier = shopDTO.identifier,
            status = shopDTO.status,
            dateShop = shopDTO.dateShop
        )
    }
}