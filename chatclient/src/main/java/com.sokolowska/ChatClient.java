package com.sokolowska;

import javax.enterprise.context.RequestScoped;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.Properties;


@RequestScoped
public class ChatClient {

    public static final String TOPIC = "jms/topic/Messages";

    public static void main(String[] args) throws JMSException, IOException, NamingException {
        if (args.length != 1) {
        } else {
            String username = args[0];
            Properties props = new Properties();
            props.setProperty("java.naming.factory.initial", "org.wildfly.naming.client.WildFlyInitialContextFactory");
            props.setProperty("java.naming.provider.url", "http-remoting://localhost:8080");
            props.setProperty("jboss.naming.client.ejb.context", "true");
            Context context = new InitialContext(props);

            Topic topic = (Topic) context.lookup(ChatClient.TOPIC);
            ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("jms/RemoteConnectionFactory");
            Connection connection = connectionFactory.createConnection();
            connection.setClientID(username);
            subscribe(connection, topic);
            publish(connection, topic, username);
        }
    }

    public static void subscribe(Connection connection, Topic topic) throws JMSException, UnknownHostException {
        Session session = connection.createSession();

        TopicSubscriber subscriber = session.createDurableSubscriber(topic, "chat room");
        subscriber.setMessageListener(new ChatMessageListener());
    }

    public static void publish(Connection connection, Topic topic, String username) throws JMSException, IOException {
        Session session = connection.createSession();
        TopicPublisher publisher = (TopicPublisher) session.createProducer(topic);
        connection.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String messageToSend = reader.readLine();
            if (messageToSend.equals("exit")){
                connection.close();
                System.exit(0);
            } else if(messageToSend.equals("room")) {
                ChatRoomDto chatRoom = new ChatRoomDto(1L, "New chat room");
                Message message = session.createObjectMessage(chatRoom);
                publisher.publish(message);
            } else if(messageToSend.startsWith("send")) {
                String path = messageToSend.split(" ")[1];
                File file = new File(path);
                BytesMessage message = session.createBytesMessage();
                message.setStringProperty("fileName", file.getName());
                message.setStringProperty("username", username);
                message.writeBytes(FileManager.readfileAsBytes(file));
                publisher.publish(message);
            } else {
                TextMessage message = session.createTextMessage("[" + username + "]: " + messageToSend);
                publisher.publish(message);
            }
        }
    }
}
