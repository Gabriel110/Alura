package br.com.gabriel.shop.application.service

import br.com.gabriel.shop.application.dto.ShopResponse
import br.com.gabriel.shop.application.dto.ShopResquest
import br.com.gabriel.shop.application.mapper.toModel
import br.com.gabriel.shop.application.mapper.toResponse
import br.com.gabriel.shop.application.mapper.toResponseV2
import br.com.gabriel.shop.application.mapper.toShopItesn
import br.com.gabriel.shop.domain.model.ShopItem
import br.com.gabriel.shop.domain.repository.ShopItemRepository
import br.com.gabriel.shop.domain.repository.ShopRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service


@Service
class ShopApplicationService(
    private val shopRepository: ShopRepository,
    private val shopItemRepository: ShopItemRepository
) {
    @Transactional
    fun createShop(shopDTO: ShopResquest): ShopResponse {
        val savedShop = shopRepository.save(shopDTO.toModel())
        val shopItems = shopDTO.toShopItesn(savedShop)
        shopItemRepository.saveAll(shopItems)
        return savedShop.toResponseV2(shopItems)
    }

    private fun shopItemRepository(shopItem: ShopItem) {
        shopItemRepository.save(shopItem)
    }

    fun getAllShops(): List<ShopResponse> {
        return shopRepository.findAll().map { it.toResponse() }
    }
}