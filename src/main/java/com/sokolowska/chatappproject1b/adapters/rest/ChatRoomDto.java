package com.sokolowska.chatappproject1b.adapters.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChatRoomDto implements Serializable {

    private String id;
    private String name;
}
