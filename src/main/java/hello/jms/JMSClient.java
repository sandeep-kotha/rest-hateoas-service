/**
 * 
 */
package hello.jms;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.util.StringUtils;

/**
 * @author sande
 *
 */
public class JMSClient {

	/**
	 * @param args
	 * @throws JMSException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws JMSException, IOException {
		// Create a ConnectionFactory
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("amqawadmin", "lwudhtfr673",
        		"failover:(tcp://10.254.176.137:8762,tcp://10.254.176.11:8762)");

        // Create a Connection
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // Create a Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create the destination (Topic or Queue)
        Destination destination = session.createQueue("pubsub.Receive");

        // Create a MessageProducer from the Session to the Topic or Queue
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        
        StringBuilder b = new StringBuilder();
        
        for(int i=0; i<100; i++)
        {
	        // Create a messages
	        String text = new String(Files.readAllBytes(Paths.get("C:\\sandeep\\gdrive\\dart.txt")));
	        String id = java.util.UUID.randomUUID().toString();
	        text = StringUtils.replace(text, "d66a7cb8-bc07-4f92-aca6-dc303ea8a431", id);
	        //text.replace("d66a7cb8-bc07-4f92-aca6-dc303ea8a431", id);
	        b.append("'").append(id).append("',");
	        System.err.println(text);
	        //String text = "Hello world! From: " + Thread.currentThread().getName() + " : " ;
	        TextMessage message = session.createTextMessage(text);
	        	
	        // Tell the producer to send the message
	        System.out.println("Sent message: "+ message.hashCode() + " : " + Thread.currentThread().getName());
	        producer.send(message);
        
        }
        System.err.println(b.toString());
        // Clean up
        session.close();
        connection.close();

	}

}
