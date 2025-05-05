package com.tk.airbnb.Airbnb.Service.Interfaces;

import com.tk.airbnb.Airbnb.DTO.HotelDTO;
import com.tk.airbnb.Airbnb.DTO.HotelInfoDTO;
import com.tk.airbnb.Airbnb.Model.Hotel;

import java.util.List;

public interface HotelService {
    HotelDTO createNewHotel(HotelDTO hotelDTO);

    HotelDTO getHotelById(Long hotelId);

    HotelDTO updateHotelById(Long hotelId, HotelDTO hotelDTO);

    void deleteHotelById(Long hotelId);

    void activateHotel(Long hotelId);

    List<HotelDTO> getAllHotels();

    HotelInfoDTO getHotelInfoById(Long hotelId);
}
