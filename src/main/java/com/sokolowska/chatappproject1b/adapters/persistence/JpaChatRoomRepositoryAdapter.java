package com.sokolowska.chatappproject1b.adapters.persistence;

import com.sokolowska.chatappproject1b.domain.ChatRoom;
import com.sokolowska.chatappproject1b.ports.ChatRoomRepository;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;


import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Singleton
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class JpaChatRoomRepositoryAdapter implements ChatRoomRepository {

    private final JpaChatRoomRepository chatRoomRepository;
    private final JpaPersistenceChatRoomMapper chatRoomMapper;


    @Override
    public ChatRoom save(ChatRoom chatRoom) {
        ChatRoomEntity entity = chatRoomMapper.toEntity(chatRoom);
        ChatRoomEntity persistedEntity = chatRoomRepository.save(entity);
        return chatRoomMapper.toDomain(persistedEntity);
    }

    @Override
    public Optional<ChatRoom> getById(String id) {
        return chatRoomRepository.getById(id).map(chatRoomMapper::toDomain);
    }
}
