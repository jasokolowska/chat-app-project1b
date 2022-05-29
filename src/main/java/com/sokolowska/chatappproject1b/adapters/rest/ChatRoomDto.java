package com.sokolowska.chatappproject1b.adapters.rest;

import lombok.*;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChatRoomDto implements Serializable {

    private long id;
    private String name;
}
