package com.sokolowska.chatappproject1b.domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ChatService {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("chatapp");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    public void save(ChatMessage chatMessage){
        entityManager.persist(chatMessage);
    }
}
