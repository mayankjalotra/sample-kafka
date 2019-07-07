package sample.kafka.samplekafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sample.kafka.producer.MessageProducerService;

@SpringBootApplication(scanBasePackages = {"sample.kafka"})
public class SampleKafkaApplication implements CommandLineRunner{

	@Autowired
	private MessageProducerService msgProducer;
	
	public static void main(String[] args) {
		SpringApplication.run(SampleKafkaApplication.class, args);
	}
	@Override
    public void run(String... args) {
		msgProducer.produce();
	}
	
}
