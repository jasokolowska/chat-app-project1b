package com.sokolowska.chatappproject1b.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ChatMessage {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    private String sender;

    private String chatRoom;
}
