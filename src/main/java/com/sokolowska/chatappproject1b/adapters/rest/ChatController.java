package com.sokolowska.chatappproject1b.adapters.rest;

import com.sokolowska.chatappproject1b.domain.ChatRoom;
import com.sokolowska.chatappproject1b.ports.ChatRoomService;

import lombok.Setter;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("chat-test")
public class ChatController {

    @Inject
    private ChatRoomService chatRoomService;
    @Inject
    private ChatRoomMapper chatRoomMapper;
    @Context
    private UriInfo uriInfo;

    @POST
    @Path("room")
    public Response createChatRoom(@Valid ChatRoomDto chatRoomDto){
        ChatRoom chatRoom = chatRoomMapper.toDomain(chatRoomDto);
        System.out.println(">>>>>>>>>>>>>>>> ChatRoom name: " + chatRoom.getName());
        System.out.println(">>>>>>>>>>>>>>>> ChatRoom id: " + chatRoom.getId());
        chatRoomService.save(chatRoom);
        return Response.created(getLocation(chatRoomDto.getName()))
                .entity(chatRoomDto)
                .build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") String id){
//        var chatRoom = chatRoomService.getById(id);
//        var chatRoomDto = chatRoomMapper.toDto(chatRoom);
//        return Response.ok(chatRoomDto).build();
        return Response.ok(). build();
    }

    @GET
    @Path("hello-world")
    public Response getGreeting(){
        String message = "This is a plain text response";

        return Response
                .status(Response.Status.OK)
                .entity(message)
                .type(MediaType.TEXT_PLAIN)
                .build();
    }


    private URI getLocation(String id) {
        return uriInfo.getAbsolutePathBuilder().path(id).build();
    }
}
