package edu.miu.cs.cs544.integration.jms;

public interface JMSSender {

	public void send(String topic, String text);

}
