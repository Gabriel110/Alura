package br.com.gabriel.shop.domain.repository

import br.com.gabriel.shop.domain.model.Shop
import org.springframework.data.jpa.repository.JpaRepository

interface ShopRepository : JpaRepository<Shop, Long> {

}
