package br.com.gabriel.shop.domain.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity(name = "shop_item")
data class ShopItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(name = "product_identifier")
    val productIdentifier: String,
    val amount: Int,
    val price: Float,
    @ManyToOne
    @JoinColumn(name = "shop_id")
    val shop: Shop
)