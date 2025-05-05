package com.tk.airbnb.Airbnb.Service.Interfaces;

import com.tk.airbnb.Airbnb.DTO.HotelPriceDTO;
import com.tk.airbnb.Airbnb.DTO.HotelSearchRequest;
import com.tk.airbnb.Airbnb.DTO.InventoryDTO;
import com.tk.airbnb.Airbnb.DTO.UpdateInventoryRequestDTO;
import com.tk.airbnb.Airbnb.Model.Room;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InventoryService {
    List<InventoryDTO> getAllInventoryByRoom(Long roomId);

    void updateInventory(Long roomId, UpdateInventoryRequestDTO updateInventoryRequestDto);

    Page<HotelPriceDTO> searchHotels(HotelSearchRequest hotelSearchRequest);

    void deleteAllInventories(Room room);

    void initializeRoomForAYear(Room room);
}
