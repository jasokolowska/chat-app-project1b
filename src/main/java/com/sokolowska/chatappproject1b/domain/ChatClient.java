package com.sokolowska.chatappproject1b.domain;

import com.sokolowska.chatappproject1b.adapters.rest.ChatController;
import com.sokolowska.chatappproject1b.adapters.rest.ChatRoomDto;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.enterprise.context.RequestScoped;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor(onConstructor_ = @Inject)
@RequestScoped
public class ChatClient {

    public static final String TOPIC = "jms/topic/Messages";

    @Inject
    private ChatController chatController;

    public static void main(String[] args) throws JMSException, IOException, NamingException {
        if (args.length != 1) {
            System.out.println("Username is required");
        } else {
            String username = args[0];
            ChatClient chatServer = new ChatClient(new ChatController());
            Context context = new InitialContext();
            Topic topic = (Topic) context.lookup(ChatClient.TOPIC);
            ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("jms/RemoteConnectionFactory");
            Connection connection = connectionFactory.createConnection();
            connection.setClientID(username);
            chatServer.subscribe(connection, topic, chatServer);
            chatServer.publish(connection, topic, username);
        }
    }

    public void subscribe(Connection connection, Topic topic, ChatClient chatServer) throws JMSException, UnknownHostException {
        Session session = connection.createSession();

        TopicSubscriber subscriber = session.createDurableSubscriber(topic, "chat room");
        subscriber.setMessageListener(new ChatService());
    }

    public void publish(Connection connection, Topic topic, String username) throws JMSException, IOException {
        Session session = connection.createSession();
        TopicPublisher publisher = (TopicPublisher) session.createProducer(topic);
        connection.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String messageToSend = reader.readLine();
            if (messageToSend.equals("exit")){
                connection.close();
                System.exit(0);
            } else if(messageToSend.equals("room")){
                chatController.createChatRoom(new ChatRoomDto(UUID.randomUUID().toString(), "new room"));
            } else {
                TextMessage message = session.createTextMessage("[" + username + "]: " + messageToSend);
                publisher.publish(message);
            }
        }
    }
}
