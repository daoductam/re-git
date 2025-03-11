package com.tamdao.api1.service;

import com.tamdao.api1.dto.CreateHotelRequest;
import com.tamdao.api1.dto.ResponseDto;
import com.tamdao.api1.dto.UpdateHotelRequest;
import com.tamdao.api1.entity.Hotel;
import com.tamdao.api1.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.midi.MidiFileFormat;
import java.util.List;

@Service
public class HotelService {

    @Autowired
    HotelRepository hotelRepository;// CREATED in IoC Container

    public Hotel getHotelById(Long id) {
        return hotelRepository.findByHotelId(id);
    }

    public Hotel createHotel(CreateHotelRequest request) {
        Hotel hotel= new Hotel();
        hotel.setHotelName(request.getHotelName());
        hotel.setRate(request.getRate());

        hotel = hotelRepository.save(hotel);

        return hotel;
    }

    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    public Hotel updateHotel(Long hotelId,UpdateHotelRequest request) {
        Hotel hotel = hotelRepository.findByHotelId(hotelId);
        if (hotel == null) {
            return null;
        }

        hotel.setHotelName(request.getHotelName());
        hotel = hotelRepository.save(hotel); //UPSERT
        return hotel;
    }

    public ResponseDto disableHotel(Long hotelId) {
        Hotel hotel = hotelRepository.findByHotelId(hotelId);
        if (hotel == null) {
            return new ResponseDto(false, "Hotel Not Found");
        }

        hotel.setStatus(false);
        hotel   = hotelRepository.save(hotel);
        hotelRepository.deleteById(hotelId  );
        return new ResponseDto(true, "successful");
    }
}
