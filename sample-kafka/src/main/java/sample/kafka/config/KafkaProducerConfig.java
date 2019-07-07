package sample.kafka.config;

import java.util.Properties;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource(value = "classpath:application.properties" ,ignoreResourceNotFound = true)
public class KafkaProducerConfig {
	
	@Value("${kafka.producer.bootstrapServers}")
	private String bootstrapServers;
	
	@Value("${kafka.producer.topicName}")
	private String topicName;
	
	
	public Properties getProducerProperties() {
		Properties producerProperties = new Properties();
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        producerProperties.put(ProducerConfig.RETRIES_CONFIG, 2);
        producerProperties.put(ProducerConfig.ACKS_CONFIG , "all");
//        producerProperties.put("min.insync.replicas", 2);//need to set a broker/topic level
//        producerProperties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
//        producerProperties.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 5);
        producerProperties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snapy"); //gzip/lz4/snappy
        producerProperties.put(ProducerConfig.LINGER_MS_CONFIG, "8");
        producerProperties.put(ProducerConfig.BATCH_SIZE_CONFIG, Integer.toString(32*1024));
        producerProperties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        producerProperties.put(ProducerConfig.MAX_BLOCK_MS_CONFIG,4000);
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return producerProperties;
	}
	
	public String getTopicName() {
		return this.topicName;
	}
}
