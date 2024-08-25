package br.com.gabriel.shop.domain.repository

import br.com.gabriel.shop.domain.model.Shop
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ShopRepository : JpaRepository<Shop, Long>{

    @Query("SELECT s FROM Shop s JOIN FETCH s.items")
    fun findAllWithItems(): List<Shop>
}
