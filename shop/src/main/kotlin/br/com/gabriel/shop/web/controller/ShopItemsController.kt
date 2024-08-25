package br.com.gabriel.shop.web.controller

import br.com.gabriel.shop.application.dto.ShopItemResponse
import br.com.gabriel.shop.application.dto.ShopResponse
import br.com.gabriel.shop.application.dto.ShopResquest
import br.com.gabriel.shop.application.service.ShopApplicationService
import br.com.gabriel.shop.application.service.ShopItemsApplicationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/shopsItems")
class ShopItemsController(
    private val shopApplicationService: ShopItemsApplicationService
) {

    @GetMapping
    fun getAllShopsItems(): ResponseEntity<List<ShopItemResponse>> {
        return ResponseEntity.ok(shopApplicationService.getAllShopsItems())
    }
}
