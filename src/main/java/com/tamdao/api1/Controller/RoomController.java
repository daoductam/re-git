package com.tamdao.api1.Controller;

import com.tamdao.api1.dto.*;
import com.tamdao.api1.entity.Hotel;
import com.tamdao.api1.entity.Room;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {
    private static List<Room> rooms = new ArrayList<>();

    // 1. Tao Room
    // Method: Post
    // Path: rooms
    @PostMapping
    public Room createRoom(@RequestBody CreateRoomRequest request) {
        Room room = new Room();
        room.setRoomId(request.getRoomId());
        room.setRoomName(request.getRoomName());
        rooms.add(room);
        return room;
    }

    // 2. lay dan sach hotel
    // Method: Get
    //Path: /api/v1/hotels

    @GetMapping
    public List<Room> getRooms(@RequestParam(required = false) String name,
                               @RequestParam(required = false) Boolean status) {
        if (name==null && status!=null) {
            return getRoomByName(name);
        } else if (name!=null && status==null) {
            return getRoomByStatus(status);

        } else if (name!=null&&status!=null) {
            return getRoomByNameAndStatus(name, status);
        }

        return rooms;
    }

    List<Room> getRoomByName(String nameRoom) {
        List<Room> result = new LinkedList<>();

        for (Room room : rooms) {
            if (room.getRoomName().equals(nameRoom)) {
                result.add(room);
            }
        }
        return result;
    }

    List<Room> getRoomByStatus(Boolean roomStatus) {
        List<Room> result = new LinkedList<>();

        for (Room room : rooms) {
            if (room.isStatus()==roomStatus) {
                result.add(room);
            }
        }
        return result;
    }

    List<Room> getRoomByNameAndStatus(String nameRoom, Boolean statusRoom) {
        List<Room> result = new LinkedList<>();

        for (Room room : rooms) {
            if (room.getRoomName().equals(nameRoom) && room.isStatus()==statusRoom) {
                result.add(room);
            }
        }
        return result;
    }

    // 3.lay thong tin cua  1 hotel
    // Method: Get
    // Path /api/v1/hotel/<hotel_id>

    @GetMapping("/{roomId}")
    public Room getRoom(@PathVariable String roomId) {
        return findRoomById(roomId);
    }

    private Room findRoomById(String roomId) {
        for (Room room: rooms) {
            if (room.getRoomId().equals(roomId)) {
                return room;
            }
        }

        return null;
    }
        // 4. Cap nhat room
        // Method: PUT
        //Path: /api/v1/room/<room-id>

    @PutMapping("/{roomId}")
    public Room updateRoom(@PathVariable String roomId,
                           @RequestBody UpdateRoomRequest request) {

        Room room = findRoomById(roomId);
        if (room==null) {
            return null;
        }

        room.setRoomName(request.getRoomName());
        room.setStatus(request.isStatus());
        return room;
    }

    //5 Vo hieu hoa 1 room
    // Method: Delete
    //Path: /api/v1/hotel/<room_id>

    @DeleteMapping("{roomId}")
    public ResponseDto deleteRoom(@PathVariable String roomId) {
        Room room = findRoomById(roomId);
        if (room==null) {
            return new ResponseDto(false, "Khong tim thay");

        }
        return new ResponseDto(true, "Successful");
    }


}
