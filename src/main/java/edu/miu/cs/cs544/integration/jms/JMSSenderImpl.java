package edu.miu.cs.cs544.integration.jms;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class JMSSenderImpl implements JMSSender{
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void send(String topic, String message){
		kafkaTemplate.send(topic, message);
	}

}
