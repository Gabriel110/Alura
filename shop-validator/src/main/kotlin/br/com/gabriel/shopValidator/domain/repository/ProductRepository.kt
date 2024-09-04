package br.com.gabriel.shopValidator.domain.repository

import br.com.gabriel.shopValidator.domain.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface ProductRepository: JpaRepository<Product, Long> {
    fun findByIdentifier(identifier: String): Product
}