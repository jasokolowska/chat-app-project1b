package com.sokolowska.chatappproject1b.adapters.persistence;

import com.sokolowska.chatappproject1b.domain.ChatRoom;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface JpaPersistenceChatRoomMapper {

    ChatRoomEntity toEntity(ChatRoom chatRoom);

    ChatRoom toDomain(ChatRoomEntity chatRoomEntity);
}
