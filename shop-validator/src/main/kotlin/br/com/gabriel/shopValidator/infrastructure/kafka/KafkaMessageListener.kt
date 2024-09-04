package br.com.gabriel.shopValidator.infrastructure.kafka

import br.com.gabriel.shop.ShopAvro
import br.com.gabriel.shop.ShopItemAvro
import br.com.gabriel.shop.StatusAvro
import br.com.gabriel.shopValidator.application.service.ProductService
import br.com.gabriel.shopValidator.domain.model.Product
import java.util.*
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component


@Component
class KafkaMessageListener(
    private val productService: ProductService,
    private val kafkaProducer: KafkaProducer
) {
    private val logger: Logger = LoggerFactory.getLogger(KafkaMessageListener::class.java)
    private var isValid: Boolean = true

    companion object {
        val correlation = UUID.randomUUID().toString()
    }

    @KafkaListener(
        topics = ["SHOP_TOPIC"],
        groupId = "group"
    )
    fun listen(message: ConsumerRecord<String, ShopAvro>, acknowledgment: Acknowledgment) {
        runCatching {
            message.value().itens.forEach {
                val product = productService.findByIdentifier(identifier = it.productIdentifier.toString())
                if(!isValidShop(it, product)){
                    isValid = false
                    shopError(message.value())
                }
            }
            if (isValid){
                shopSuccess(message.value())
            }
        }.onFailure {
            logger.error("[Error]::${it.stackTrace[0]}, ${it.message}, correlationId: $correlation")
        }.onSuccess {
            logger.info("sucess!")
            acknowledgment.acknowledge()
        }
    }

    private fun isValidShop(
        item: ShopItemAvro,
        product: Product?
    ): Boolean {
        return product != null &&
                product.amount >= item.amount
    }

    private fun shopError(product: ShopAvro) {
        val productUpdate = ShopAvro.newBuilder()
            .setItens(product.itens)
            .setStatus(StatusAvro.CANCELLED)
            .setDateShop(product.dateShop)
            .setIdentifier(product.identifier)
            .build()
        kafkaProducer.sendMessage(productUpdate)
    }

    private fun shopSuccess(product: ShopAvro) {
        val productUpdate = ShopAvro.newBuilder()
            .setItens(product.itens)
            .setStatus(StatusAvro.APPROVED)
            .setDateShop(product.dateShop)
            .setIdentifier(product.identifier)
            .build()
        println(productUpdate)
        kafkaProducer.sendMessage(productUpdate)
    }
}