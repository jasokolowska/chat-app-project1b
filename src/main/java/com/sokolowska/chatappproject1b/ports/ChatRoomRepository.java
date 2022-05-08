package com.sokolowska.chatappproject1b.ports;

import com.sokolowska.chatappproject1b.domain.ChatRoom;

import java.util.Optional;

public interface ChatRoomRepository {

    ChatRoom save(ChatRoom chatRoom);

    Optional<ChatRoom> getById(String id);
}
