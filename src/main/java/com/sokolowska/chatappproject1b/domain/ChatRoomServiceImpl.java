package com.sokolowska.chatappproject1b.domain;

import com.sokolowska.chatappproject1b.ports.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.ejb.Stateless;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


@Stateless
public class ChatRoomServiceImpl implements ChatRoomService {

    @Setter
    @PersistenceContext(unitName = "chatapp")
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(ChatRoom chatRoom) {
        entityManager.persist(chatRoom);
    }

    @Override
    public ChatRoom getById(String id) {
        return null;
    }
}
