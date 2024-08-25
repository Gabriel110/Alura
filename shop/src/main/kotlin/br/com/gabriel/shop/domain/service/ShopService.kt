package br.com.gabriel.shop.domain.service

import br.com.gabriel.shop.domain.model.Shop
import br.com.gabriel.shop.domain.repository.ShopRepository
import org.springframework.stereotype.Service

@Service
class ShopService(private val shopRepository: ShopRepository) {

    fun createShop(shop: Shop): Shop {
        return shopRepository.save(shop)
    }

    fun listShop(): List<Shop>{
        return shopRepository.findAll()
    }
}
