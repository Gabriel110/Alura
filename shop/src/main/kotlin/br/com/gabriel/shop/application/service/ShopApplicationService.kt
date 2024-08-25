package br.com.gabriel.shop.application.service

import br.com.gabriel.shop.application.dto.ShopResponse
import br.com.gabriel.shop.application.dto.ShopResquest
import br.com.gabriel.shop.application.mapper.toModel
import br.com.gabriel.shop.application.mapper.toResponse
import br.com.gabriel.shop.domain.repository.ShopItemRepository
import br.com.gabriel.shop.domain.repository.ShopRepository
import org.springframework.stereotype.Service


@Service
class ShopApplicationService(
    private val shopRepository: ShopRepository,
    private val shopItemRepository: ShopItemRepository
) {
    fun createShop(shopDTO: ShopResquest): ShopResponse {
        val shop = shopDTO.toModel()
        val savedShop = shopRepository.save(shop)
        return savedShop.toResponse()
    }

    fun getAllShops(): List<ShopResponse> {
        return shopRepository.findAllWithItems().map { it.toResponse() }
    }
}