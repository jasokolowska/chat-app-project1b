package com.sokolowska.chatappproject1b.domain;

import com.sokolowska.chatappproject1b.adapters.rest.ChatRoomDto;

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
import java.util.UUID;

public class ChatServer {

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
        }
    }

    public static void subscribe(Connection connection, Topic topic) throws JMSException, UnknownHostException {
        Session session = connection.createSession();

        TopicSubscriber subscriber = session.createDurableSubscriber(topic, "chat room");
        subscriber.setMessageListener(new ChatMessageListener());
    }
}
