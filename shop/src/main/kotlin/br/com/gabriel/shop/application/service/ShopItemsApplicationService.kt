package br.com.gabriel.shop.application.service

import br.com.gabriel.shop.application.dto.ShopItemResponse
import br.com.gabriel.shop.application.mapper.toResponse
import br.com.gabriel.shop.domain.repository.ShopItemRepository
import org.springframework.stereotype.Service


@Service
class ShopItemsApplicationService(
    private val shopItemRepository: ShopItemRepository
) {

    fun getAllShopsItems(): List<ShopItemResponse> {
        return shopItemRepository.findAll().map { it.toResponse() }
    }
}