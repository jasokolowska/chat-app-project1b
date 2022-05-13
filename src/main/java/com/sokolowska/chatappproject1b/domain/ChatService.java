package com.sokolowska.chatappproject1b.domain;

import com.sokolowska.chatappproject1b.adapters.rest.ChatRoomDto;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "Messages")
})
public class ChatService implements MessageListener {

//    private Client client = ClientBuilder.newClient();

    @Override
    public void onMessage(Message message) {
        try {
            if (message.isBodyAssignableTo(ChatRoomDto.class)) {
                System.out.println("OnMessage - before creating a room ...");
//                int status = client.target("http://localhost:8080/chat-app-project1b-1.0-SNAPSHOT/api/chat")
//                        .request(MediaType.APPLICATION_JSON_TYPE)
//                        .post(Entity.entity(ChatRoomDto.class, MediaType.APPLICATION_JSON_TYPE))
//                        .getStatus();
                System.out.println(">>>>>>>> Status of room creation: ");
            } else if(message instanceof BytesMessage) {
                System.out.println("There is message to download...");
                BytesMessage bytesMessage = (BytesMessage) message;
                //TODO: implement method which wile write file on a server
//                bytesMessage.readBytes()
            } else {
                System.out.println(((TextMessage) message).getText());
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public byte[] readfileAsBytes(File file) throws IOException {
        try (RandomAccessFile accessFile = new RandomAccessFile(file, "r")) {
            byte[] bytes = new byte[(int) accessFile.length()];
            accessFile.readFully(bytes);
            return bytes;
        }
    }
}
