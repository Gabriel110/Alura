package br.com.gabriel.shop.domain.repository

import br.com.gabriel.shop.domain.model.ShopItem
import org.springframework.data.jpa.repository.JpaRepository

interface ShopItemRepository : JpaRepository<ShopItem, Long>{


}