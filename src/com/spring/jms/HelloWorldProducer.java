package com.spring.jms;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;


public class HelloWorldProducer {

	public static void main(String[] args) {
		
		try {
			//Create a connection Factory
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://DESKTOP-EV88OST:61616");
			
			//Create a connection
			Connection connection = connectionFactory.createConnection();
			connection.start();
			
			//Create a Session
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			//Create the destination (Topic or Queue)
			Destination destination = session.createQueue("HELLOWORLD1.TESTQ");
			
			//Create a MessageProducer from the session to the Topic or Queue
			MessageProducer producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			
			//Create a message
			String text = "Hello World! From "+ Thread.currentThread().getName();
			TextMessage message = session.createTextMessage(text);
			
			//Producer sending the message
			System.out.println("Sent message: "+message.hashCode()+" : "+Thread.currentThread().getName());
			producer.send(message);
			
			session.close();
			connection.close();
		}catch(JMSException e) {
			System.out.println("Caught : "+e);
			e.printStackTrace();
		}
		
		
		
		
		
	}
}
