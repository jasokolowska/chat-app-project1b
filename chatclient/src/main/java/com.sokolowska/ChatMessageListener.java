package com.sokolowska;


import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Singleton;
import javax.jms.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Singleton
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "Messages")
})
public class ChatMessageListener implements MessageListener {

    private Client client = ClientBuilder.newClient();

    @Override
    public void onMessage(Message message) {
        try {
            if (message.isBodyAssignableTo(ChatRoomDto.class)) {
                System.out.println("OnMessage - before creating a room ...");

                ChatRoomDto chatRoom = message.getBody(ChatRoomDto.class);

                int status = client.target("http://localhost:8080/chat-app-project1b-1.0-SNAPSHOT/api/chat-test/room")
                        .request()
                        .accept(BinaryMapper.MEDIA_TYPE)
                        .post(Entity.entity(chatRoom, MediaType.APPLICATION_JSON))
                        .getStatus();
                System.out.println(">>>>>>>> Status of room creation: " + status);
            } else if(message instanceof BytesMessage) {
                System.out.println("There is file to download..." + message.getStringProperty("fileName"));
                BytesMessage bytesMessage = (BytesMessage) message;
                handleBytesMessage(bytesMessage, "new_file");
                System.out.println("File \"" + message.getStringProperty("fileName") + " \" received");
            } else {
                //TODO: add saving message in the db
                System.out.println(((TextMessage) message).getText());
                ChatMessage chatMessage = new ChatMessage(1L, ((TextMessage) message).getText(), "sender", "chatroom");
//                chatService.save(chatMessage);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private void handleBytesMessage(BytesMessage bytesMessage, String filename) throws JMSException {
        int dataSize = (int) bytesMessage.getBodyLength();
        byte[] buffer = new byte[dataSize];
        bytesMessage.readBytes(buffer, dataSize);
        String outputFileName = "C:\\Development\\Lufthansa\\chat-app-project1b\\src\\main\\resources\\receivedFiles\\" + filename;
        try {
            FileManager.writeFile(buffer, outputFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("File received");
    }
}
