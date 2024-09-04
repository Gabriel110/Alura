package br.com.gabriel.shopValidator.application.service

import br.com.gabriel.shopValidator.domain.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
   private val productRepository: ProductRepository
) {

    fun findByIdentifier(identifier: String) = productRepository.findByIdentifier(identifier)
}