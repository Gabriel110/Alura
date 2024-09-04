package br.com.gabriel.shop.web.controller

import br.com.gabriel.shop.application.dto.ShopResponse
import br.com.gabriel.shop.application.dto.ShopResquest
import br.com.gabriel.shop.application.service.ShopApplicationService
import br.com.gabriel.shop.infrastructure.kafka.KafkaProducer
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/shops")
class ShopController(
    private val shopApplicationService: ShopApplicationService,
    private val kafkaProducer: KafkaProducer
) {

    @PostMapping
    fun createShop(@RequestBody shopDTO: ShopResquest): ResponseEntity<ShopResponse> {
        val shop = shopApplicationService.createShop(shopDTO)
        kafkaProducer.sendMessage(shop)
        return ResponseEntity.ok(shop)
    }

    @GetMapping
    fun getAllShops(): ResponseEntity<List<ShopResponse>> {
        return ResponseEntity.ok(shopApplicationService.getAllShops())
    }
}
