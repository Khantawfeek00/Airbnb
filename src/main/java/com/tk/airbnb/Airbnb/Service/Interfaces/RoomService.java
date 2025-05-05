package com.tk.airbnb.Airbnb.Service.Interfaces;

import com.tk.airbnb.Airbnb.DTO.RoomDTO;

import java.util.List;

public interface RoomService {
    RoomDTO createNewRoom(Long hotelId, RoomDTO roomDTO);

    List<RoomDTO> getAllRoomdsInHotel(Long hotelId);

    RoomDTO getRoomById(Long hotelId, Long roomId);

    void deleteRoomById(Long hotelId, Long roomId);

    RoomDTO updateRoomById(Long hotelId, Long roomId, RoomDTO roomDTO);
}
