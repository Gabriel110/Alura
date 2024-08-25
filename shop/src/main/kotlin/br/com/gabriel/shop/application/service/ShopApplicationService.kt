package br.com.gabriel.shop.application.service

import br.com.gabriel.shop.application.dto.ShopResponse
import br.com.gabriel.shop.application.dto.ShopResquest
import br.com.gabriel.shop.application.mapper.ShopMapper
import br.com.gabriel.shop.domain.repository.ShopRepository
import org.springframework.stereotype.Service


@Service
class ShopApplicationService(
    private val shopRepository: ShopRepository,
    private val shopMapper: ShopMapper
) {
    fun createShop(shopDTO: ShopResquest): ShopResponse {
        val shop = shopMapper.toModel(shopDTO)
        val savedShop = shopRepository.save(shop)
        return shopMapper.toDTO(savedShop)
    }

    fun getAllShops(): List<ShopResponse> {
        return shopRepository.findAll().map { shopMapper.toDTO(it) }
    }
}