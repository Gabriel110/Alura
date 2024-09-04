package br.com.gabriel.shopValidator.config

import br.com.gabriel.shop.ShopAvro
import io.confluent.kafka.serializers.KafkaAvroDeserializer
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig
import io.confluent.kafka.serializers.KafkaAvroSerializer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.listener.ContainerProperties
import org.springframework.kafka.listener.DefaultErrorHandler


@Configuration
class KafkaConfig(
    @Value("\${kafka.bootstrapAddress:http://localhost:9092}")
    private val bootstrapAddress:String,
    @Value("\${kafka.schemaRegistry:http://localhost:8181}")
    private val schemaRegistry:String
) {

    @Bean
    fun producerFactory(): ProducerFactory<String, ShopAvro> {
        val configProps = mapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapAddress,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to KafkaAvroSerializer::class.java,
            ProducerConfig.METRICS_RECORDING_LEVEL_CONFIG to "INFO",
            ProducerConfig.METRIC_REPORTER_CLASSES_CONFIG to "org.apache.kafka.common.metrics.JmxReporter",
            ProducerConfig.CLIENT_ID_CONFIG to "shop-api",
            "schema.registry.url" to schemaRegistry
        )
        return DefaultKafkaProducerFactory(configProps)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, ShopAvro> {
        return KafkaTemplate(producerFactory())
    }


    @Bean
    fun consumerFactory(): ConsumerFactory<String, ShopAvro> {
        val configProps = mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapAddress,
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to KafkaAvroDeserializer::class.java,
            ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to "earliest",
            ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG to false,
            "schema.registry.url" to schemaRegistry,
            KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG to "true"
        )
        return DefaultKafkaConsumerFactory(configProps)
    }


    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, ShopAvro> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, ShopAvro>()
        factory.consumerFactory = consumerFactory()
        factory.containerProperties.ackMode = ContainerProperties.AckMode.MANUAL
        factory.setCommonErrorHandler(DefaultErrorHandler())
        factory.setConcurrency(System.getenv("KAFKA_CONCURRENCY")?.toIntOrNull() ?: 2)
        return factory
    }
}