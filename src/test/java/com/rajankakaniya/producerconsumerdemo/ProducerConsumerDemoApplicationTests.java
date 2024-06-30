package com.rajankakaniya.producerconsumerdemo;

import com.rajankakaniya.producerconsumerdemo.consumer.MessageConsumer;
import com.rajankakaniya.producerconsumerdemo.producer.MessageProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = "${demo.topic.name}")
class ProducerConsumerDemoApplicationTests {

	@Autowired
	private MessageProducer producer;

	@Autowired
	private MessageConsumer consumer;

	@Test
	public void testSuccessfulMessageProcessing() throws InterruptedException {
		producer.sendMessage("Test message");
		Thread.sleep(1000); // Wait for the message to be consumed

		assertEquals(1, consumer.getSuccessCount());
		assertEquals(0, consumer.getErrorCount());
	}

	@Test
	public void testErrorMessageProcessing() throws InterruptedException {
		producer.sendMessage(null);
		Thread.sleep(1000); // Wait for the message to be consumed

		assertEquals(1, consumer.getErrorCount());
	}

}
