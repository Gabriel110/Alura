package br.com.gabriel.shop.domain.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import java.time.LocalDate

@Entity
data class Shop(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val identifier: String,
    val status: Status,
    @Column(name = "date_shop")
    val dateShop: LocalDate,
    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], mappedBy = "shop")
    val items: List<ShopItem>
){
    enum class Status(value:String) {
        PENDING("PENDING"),
        APPROVED("APPROVED"),
        DELIVERED("DELIVERED"),
        CANCELLED("CANCELLED");
    }
}
