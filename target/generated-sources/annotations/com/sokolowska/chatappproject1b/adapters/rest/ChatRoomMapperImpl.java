package com.sokolowska.chatappproject1b.adapters.rest;

import com.sokolowska.chatappproject1b.domain.ChatRoom;
import javax.annotation.Generated;
import javax.enterprise.context.ApplicationScoped;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-28T21:45:55+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@ApplicationScoped
public class ChatRoomMapperImpl implements ChatRoomMapper {

    @Override
    public ChatRoom toDomain(ChatRoomDto chatRoomDto) {
        if ( chatRoomDto == null ) {
            return null;
        }

        ChatRoom chatRoom = new ChatRoom();

        chatRoom.setName( chatRoomDto.getName() );

        return chatRoom;
    }

    @Override
    public ChatRoomDto toDto(ChatRoom chatRoom) {
        if ( chatRoom == null ) {
            return null;
        }

        ChatRoomDto chatRoomDto = new ChatRoomDto();

        chatRoomDto.setId( chatRoom.getId() );
        chatRoomDto.setName( chatRoom.getName() );

        return chatRoomDto;
    }
}
