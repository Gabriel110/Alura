package br.com.gabriel.shop.infrastructure.kafka

import br.com.gabriel.shop.ShopAvro
import br.com.gabriel.shop.application.dto.ShopResponse
import br.com.gabriel.shop.application.mapper.ShopAvroMapper
import br.com.gabriel.shop.application.mapper.ShopAvroMapper.Companion.toAvroMessage
import org.apache.kafka.common.KafkaException
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service


@Service
class KafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, ShopAvro>,
    private val shopAvroMapper: ShopAvroMapper
) {

    private val SHOP_TOPIC_NAME: String = "SHOP_TOPIC"

    fun sendMessage(shop: ShopResponse) {
        runCatching {
            val message = shopAvroMapper.buildMessage(shop.toAvroMessage(), SHOP_TOPIC_NAME)
            kafkaTemplate.send(message)
        }.onFailure {
            throw KafkaException(it)
        }
    }
}