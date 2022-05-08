package com.sokolowska.chatappproject1b.adapters.rest;

import com.sokolowska.chatappproject1b.domain.ChatRoom;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface ChatRoomMapper {

    ChatRoom toDomain(ChatRoomDto chatRoomDto);
    ChatRoomDto toDto(ChatRoom chatRoom);

}
