package com.sokolowska.chatappproject1b.adapters.persistence;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity(name = "ChatRoom")
@EqualsAndHashCode(of = "id")
@Setter
@Getter
public class ChatRoomEntity {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
}
