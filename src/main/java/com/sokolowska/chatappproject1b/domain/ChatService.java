package com.sokolowska.chatappproject1b.domain;

import com.sokolowska.chatappproject1b.adapters.rest.ChatRoomDto;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "Messages")
})
public class ChatService implements MessageListener {

    private Client client = ClientBuilder.newClient();

    @Override
    public void onMessage(Message message) {
        try {
            if (message.isBodyAssignableTo(ChatRoomDto.class)) {
                int status = client.target("http://localhost:8080/chat-app-project1b-1.0-SNAPSHOT/api/chat")
                        .request(MediaType.APPLICATION_JSON_TYPE)
                        .post(Entity.entity(ChatRoomDto.class, MediaType.APPLICATION_JSON_TYPE))
                        .getStatus();
                System.out.println(">>>>>>>> Status of room creation: " + status);
            } else {
                System.out.println(((TextMessage) message).getText());
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
