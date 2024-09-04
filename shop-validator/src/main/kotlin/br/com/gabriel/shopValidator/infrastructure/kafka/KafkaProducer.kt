package br.com.gabriel.shopValidator.infrastructure.kafka

import br.com.gabriel.shop.ShopAvro
import org.apache.kafka.common.KafkaException
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service


@Service
class KafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, ShopAvro>
) {

    fun sendMessage(shop: ShopAvro) {
        runCatching {
            kafkaTemplate.send("SHOP_TOPIC_EVENT", shop)
        }.onFailure {
            throw KafkaException(it)
        }
    }
}