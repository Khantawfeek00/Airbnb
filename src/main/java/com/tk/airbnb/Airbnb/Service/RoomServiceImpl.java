package com.tk.airbnb.Airbnb.Service;

import com.tk.airbnb.Airbnb.DTO.RoomDTO;
import com.tk.airbnb.Airbnb.Exception.ResourceNotFoundException;
import com.tk.airbnb.Airbnb.Model.Hotel;
import com.tk.airbnb.Airbnb.Model.Room;
import com.tk.airbnb.Airbnb.Repository.HotelRepository;
import com.tk.airbnb.Airbnb.Repository.RoomRepository;
import com.tk.airbnb.Airbnb.Service.Interfaces.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomServiceImpl implements RoomService {

    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;

    @Override
    public RoomDTO createNewRoom(Long hotelId, RoomDTO roomDTO) {
        log.info("Creating new room for hotel with ID: {}", hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: " + hotelId));

        Room room = modelMapper.map(roomDTO, Room.class);
        room.setHotel(hotel);
        roomRepository.save(room);
        return modelMapper.map(room, RoomDTO.class);
    }

    @Override
    public List<RoomDTO> getAllRoomdsInHotel(Long hotelId) {
        log.info("Reading all rooms for hotel with ID: {}", hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: " + hotelId));

        return hotel.getRooms()
                .stream()
                .map(room -> modelMapper.map(room, RoomDTO.class)).toList();
    }

    @Override
    public RoomDTO getRoomById(Long hotelId, Long roomId) {
        log.info("Reading room with ID: {}", roomId);
        Room room = roomRepository
                .findByHotelIdAndRoomId(hotelId, roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: " + roomId));
        return modelMapper.map(room, RoomDTO.class);
    }

    @Override
    @Transactional
    public void deleteRoomById(Long hotelId, Long roomId) {
        log.info("Deleting room with ID: {}", roomId);
        Room room = roomRepository
                .findByHotelIdAndRoomId(hotelId, roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: " + roomId));

//        inventoryService.deleteAllInventories(room);
        roomRepository.delete(room);
    }

    @Override
    public RoomDTO updateRoomById(Long hotelId, Long roomId, RoomDTO roomDTO) {
        log.info("Updating room with ID: {}", roomId);
        Room room = roomRepository
                .findByHotelIdAndRoomId(hotelId, roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: " + roomId));

        modelMapper.map(roomDTO, room);
        room.setId(roomId);
        //TODO: if price or inventory is updated, then update the inventory for this room
        room = roomRepository.save(room);
        return modelMapper.map(room, RoomDTO.class);
    }


}
