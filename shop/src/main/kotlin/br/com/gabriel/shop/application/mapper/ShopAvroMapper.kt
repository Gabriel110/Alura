package br.com.gabriel.shop.application.mapper


import br.com.gabriel.shop.ShopAvro
import br.com.gabriel.shop.ShopItemAvro
import br.com.gabriel.shop.StatusAvro
import br.com.gabriel.shop.application.dto.ShopResponse
import java.time.LocalDateTime
import java.util.UUID
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component

@Component
class ShopAvroMapper {

    companion object {
        fun ShopResponse.toAvroMessage(): ShopAvro {
            val itens = itens.map {
                ShopItemAvro.newBuilder()
                    .setId(it.id)
                    .setPrice(it.price)
                    .setAmount(it.amount)
                    .setProductIdentifier(it.productIdentifier)
                    .build()
            }
            return ShopAvro.newBuilder()
                .setDateShop(dateShop.toString())
                .setStatus(StatusAvro.valueOf(status.toString()))
                .setIdentifier(identifier)
                .setItens(itens)
                .build()
        }
    }

    fun buildMessage(event: ShopAvro, topic: String): Message<ShopAvro> {
        return MessageBuilder.withPayload(event)
            .setHeader(KafkaHeaders.TOPIC, topic)
            .setHeader("transactionid", UUID.randomUUID().toString())
            .setHeader("correlationid", UUID.randomUUID().toString())
            .setHeader("source", "hv5")
            .setHeader("type","br.com.gabriel.usuario")
            .setHeader("eventversion","v1.0")
            .setHeader("specversion","001")
            .setHeader("time", LocalDateTime.now().toString())
            .setHeader("datacontenttype","application/avro")
            .build()
    }
}