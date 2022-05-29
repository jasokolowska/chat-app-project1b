package com.sokolowska;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChatRoomDto implements Serializable {

    private long id;
    private String name;
}
