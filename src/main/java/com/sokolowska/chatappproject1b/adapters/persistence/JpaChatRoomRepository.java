package com.sokolowska.chatappproject1b.adapters.persistence;

import lombok.Setter;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Singleton
public class JpaChatRoomRepository {

    @Setter
    @PersistenceContext
    private EntityManager entityManager;

    public ChatRoomEntity save(ChatRoomEntity chatRoomEntity) {
        entityManager.persist(chatRoomEntity);
        return chatRoomEntity;
    }

    public Optional<ChatRoomEntity> getById(String id){
        return Optional.ofNullable(entityManager.find(ChatRoomEntity.class, id));
    }
}
