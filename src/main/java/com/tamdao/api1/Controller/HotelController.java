package com.tamdao.api1.Controller;


import com.tamdao.api1.dto.CreateHotelRequest;
import com.tamdao.api1.dto.ResponseDto;
import com.tamdao.api1.dto.UpdateHotelRequest;
import com.tamdao.api1.entity.Hotel;
import com.tamdao.api1.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/hotels")
public class HotelController {

    private static List<Hotel> hotels = new ArrayList<>();

    @Autowired
    HotelService hotelService;

    // 1. Tao hotel
    // Method: Post
    // Path: hotels
    @PostMapping
    public Hotel createHotel(@RequestBody CreateHotelRequest request) {

        return hotelService.createHotel(request);
    }

    // 2. lay dan sach hotel
    // Method: Get
    //Path: /api/v1/hotels

    @GetMapping
    public List<Hotel> getHotels(@RequestParam(required = false) Integer rate,
                                 @RequestParam(required = false) Boolean status) {//nullable

        return hotelService.getAll();
    }

    List<Hotel> findHotelsByRate(Integer rate) {
        List<Hotel> result = new LinkedList<>();

        for (Hotel hotel: hotels) {
            if (hotel.getRate() == rate ) {
                result.add(hotel);
            }
        }
        return result;
    }

    List<Hotel> findHotelsByStatus(Boolean status) {
        List<Hotel> result = new LinkedList<>();

        for (Hotel hotel: hotels) {
            if (hotel.isStatus() == status ) {
                result.add(hotel);
            }
        }
        return result;
    }

    List<Hotel> findHotelsByStatusAndRate(Boolean status, Integer rate) {
        List<Hotel> result = new LinkedList<>();

        for (Hotel hotel: hotels) {
            if (hotel.isStatus() == status && hotel.getRate() == rate ) {
                result.add(hotel);
            }
        }
        return result;
    }



    // 3.lay thong tin cua  1 hotel
    // Method: Get
    // Path /api/v1/hotel/<hotel_id>
    @GetMapping("/{hotelId}")
    public Hotel getHotel(@PathVariable Long hotelId) {

//        return findHotelById(hotelId);
        return hotelService.getHotelById(hotelId);
    }

    // 4. Cap nhat hotel
    // Method: PUT
    //Path: /api/v1/hotels/<hotel-id>
    @PutMapping("/{hotelId}")
    public Hotel updateHotel(@PathVariable Long hotelId,
                             @RequestBody UpdateHotelRequest request) {
//        Hotel hotel = findHotelById(hotelId);
//        if (hotel == null) {
//            return null;
//        }
//
//        hotel.setHotelName(request.getHotelName());
//        hotel.setStatus(request.isStatus());
//
//        return hotel;

        return hotelService.updateHotel(hotelId, request);
    }

    //5 Vo hieu hoa 1 hotel
    // Method: Delete
    //Path: /api/v1/hotel/<hotel_id>
    //@DeleteMapping
    //

    @DeleteMapping("/{hotelId}")
    public ResponseDto disableHotel(@PathVariable long hotelId) {
//        Hotel hotel = findHotelById(hotelId);
//        if (hotel == null) {
//            return new ResponseDto(false, "Hotel Not Found");
//        }
//        hotel.setStatus(false);
//        return new ResponseDto(true, "successful");

        return hotelService.disableHotel(hotelId);
    }

    private Hotel findHotelById(long hotelId) {
        for (Hotel hotel: hotels) {
            if (hotel.getHotelId() == hotelId) {
                return hotel;
            }
        }
        return null;
    }



}
