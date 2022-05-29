package com.sokolowska.chatappproject1b.adapters.persistence;

import com.sokolowska.chatappproject1b.domain.ChatRoom;
import javax.annotation.Generated;
import javax.enterprise.context.ApplicationScoped;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-28T21:45:55+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@ApplicationScoped
public class JpaPersistenceChatRoomMapperImpl implements JpaPersistenceChatRoomMapper {

    @Override
    public ChatRoomEntity toEntity(ChatRoom chatRoom) {
        if ( chatRoom == null ) {
            return null;
        }

        ChatRoomEntity chatRoomEntity = new ChatRoomEntity();

        chatRoomEntity.setId( chatRoom.getId() );
        chatRoomEntity.setName( chatRoom.getName() );

        return chatRoomEntity;
    }

    @Override
    public ChatRoom toDomain(ChatRoomEntity chatRoomEntity) {
        if ( chatRoomEntity == null ) {
            return null;
        }

        ChatRoom chatRoom = new ChatRoom();

        chatRoom.setId( chatRoomEntity.getId() );
        chatRoom.setName( chatRoomEntity.getName() );

        return chatRoom;
    }
}
