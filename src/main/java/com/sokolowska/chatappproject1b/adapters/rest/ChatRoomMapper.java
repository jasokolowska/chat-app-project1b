package com.sokolowska.chatappproject1b.adapters.rest;

import com.sokolowska.chatappproject1b.domain.ChatRoom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface ChatRoomMapper {

    @Mapping(source = "id", target = "id", ignore = true)
    ChatRoom toDomain(ChatRoomDto chatRoomDto);

    ChatRoomDto toDto(ChatRoom chatRoom);

}
