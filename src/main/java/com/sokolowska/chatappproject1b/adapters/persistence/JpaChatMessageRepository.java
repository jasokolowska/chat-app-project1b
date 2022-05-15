package com.sokolowska.chatappproject1b.adapters.persistence;

import com.sokolowska.chatappproject1b.domain.ChatMessage;
import lombok.Setter;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Singleton
public class JpaChatMessageRepository {

    @Setter
    @PersistenceContext
    private EntityManager entityManager;

    public ChatMessage save(ChatMessage chatMessage) {
        entityManager.persist(chatMessage);
        return chatMessage;
    }
}
