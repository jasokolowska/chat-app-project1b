package com.sokolowska.chatappproject1b.ports;

import com.sokolowska.chatappproject1b.domain.ChatRoom;

public interface ChatRoomService {

    void save(ChatRoom chatRoom);

    ChatRoom getById(String id);
}
