package com.sokolowska.chatappproject1b.adapters.rest;

import com.sokolowska.chatappproject1b.domain.ChatRoom;
import com.sokolowska.chatappproject1b.ports.ChatRoomService;
import jakarta.inject.Inject;

import javax.enterprise.context.RequestScoped;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;


@Path("/chat")
public class ChatController {

//    @Inject
//    private ChatRoomService chatRoomService;
    @Inject
    private ChatRoomMapper chatRoomMapper;
    @Context
    private UriInfo uriInfo;

    @POST
    public Response createChatRoom(ChatRoomDto chatRoomDto){
        ChatRoom chatRoom = chatRoomMapper.toDomain(chatRoomDto);
//        chatRoomService.save(chatRoom);
        return Response.created(getLocation(chatRoomDto.getId()))
                .entity(chatRoomDto)
                .build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") String id){
//        var chatRoom = chatRoomService.getById(id);
//        var chatRoomDto = chatRoomMapper.toDto(chatRoom);
//        return Response.ok(chatRoomDto).build();
        return Response.ok().build();
    }


    private URI getLocation(String id) {
        return uriInfo.getAbsolutePathBuilder().path(id).build();
    }
}
