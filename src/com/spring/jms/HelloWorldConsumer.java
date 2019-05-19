package com.spring.jms;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class HelloWorldConsumer {

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
			
			//Create a message Consumer from the session  to the Topic or Queue
			MessageConsumer consumer = session.createConsumer(destination);
			
			//wait for the message
			Message message = consumer.receive(1000);
			
			if(message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage)message;
				String text = textMessage.getText();
				System.out.println("Received :"+text);				
			}else {
				System.out.println("Received :"+message);
			}
			consumer.close();
			session.close();
			connection.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
