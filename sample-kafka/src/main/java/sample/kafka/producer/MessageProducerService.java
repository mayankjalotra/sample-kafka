package sample.kafka.producer;


import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.kafka.config.KafkaProducerConfig;

@Service
public class MessageProducerService {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageProducerService.class);
	
	@Autowired
	private KafkaProducerConfig producerConfig;
	
	private String topicName;
	
	KafkaProducer<String, String> producer;
	
	@PostConstruct
	private void init() {
		Properties producerProperties = producerConfig.getProducerProperties();
		this.topicName = producerConfig.getTopicName();
		this.producer = new KafkaProducer<>(producerProperties);
	}

	public void produce() {
		for(int i =0;i<10;i++) {
		Callback callback = (RecordMetadata metadata, Exception exception) -> {
//			synchronized (this) {
			if(exception!= null) {
				logger.error("Exception while producing record");
			}else if(metadata != null){
				logger.info(topicName+"::"+metadata.partition()+"::"+metadata.offset());
			}
//			}
		};
				
		producer.send(new ProducerRecord<String, String>(topicName, "My name is Mayank"+i),callback);
		}
		
	}
	
	
}
