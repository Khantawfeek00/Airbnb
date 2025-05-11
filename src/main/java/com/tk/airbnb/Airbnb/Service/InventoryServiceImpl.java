package com.tk.airbnb.Airbnb.Service;

import com.tk.airbnb.Airbnb.DTO.HotelPriceDTO;
import com.tk.airbnb.Airbnb.DTO.HotelSearchRequest;
import com.tk.airbnb.Airbnb.DTO.InventoryDTO;
import com.tk.airbnb.Airbnb.DTO.UpdateInventoryRequestDTO;
import com.tk.airbnb.Airbnb.Exception.ResourceNotFoundException;
import com.tk.airbnb.Airbnb.Model.Hotel;
import com.tk.airbnb.Airbnb.Model.Inventory;
import com.tk.airbnb.Airbnb.Model.Room;
import com.tk.airbnb.Airbnb.Model.User;
import com.tk.airbnb.Airbnb.Repository.HotelMinPriceRepository;
import com.tk.airbnb.Airbnb.Repository.InventoryRepository;
import com.tk.airbnb.Airbnb.Repository.RoomRepository;
import com.tk.airbnb.Airbnb.Service.Interfaces.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import static com.tk.airbnb.Airbnb.Util.AppUtils.getCurrentUser;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;
    private final HotelMinPriceRepository hotelMinPriceRepository;

    @Override
    public void initializeRoomForAYear(Room room) {
        log.info("Initializing inventory for room with ID: {}", room.getId());
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusYears(1);

        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            Inventory inventory = Inventory.builder().hotel(room.getHotel()).room(room).bookCount(0).reservedCount(0).city(room.getHotel().getCity()).date(startDate).price(room.getBasePrice()).surgeFactor(BigDecimal.ONE).totalCount(room.getTotalCount()).closed(false).build();

            inventoryRepository.save(inventory);
        }
    }


    @Override
    public List<InventoryDTO> getAllInventoryByRoom(Long roomId) {
        log.info("Getting all inventory for room with ID: {}", roomId);
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: " + roomId));

        User user = getCurrentUser();
        if (!user.equals(room.getHotel().getOwner())) {
            throw new AccessDeniedException("You do not have permission to view this inventory");
        }
        return inventoryRepository.findByRoomOrderByDate(room).stream().map(inventory -> modelMapper.map(inventory, InventoryDTO.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateInventory(Long roomId, UpdateInventoryRequestDTO updateInventoryRequestDto) {
        log.info("Updating inventory for room with ID: {}", roomId);
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: " + roomId));

        User user = getCurrentUser();
        if (!user.equals(room.getHotel().getOwner())) {
            throw new AccessDeniedException("You do not have permission to view this inventory");
        }

        inventoryRepository.getInventoryAndLockBeforeUpdate(roomId, updateInventoryRequestDto.getStartDate(), updateInventoryRequestDto.getEndDate());

        inventoryRepository.updateInventory(roomId, updateInventoryRequestDto.getStartDate(), updateInventoryRequestDto.getEndDate(), updateInventoryRequestDto.getClosed(), updateInventoryRequestDto.getSurgeFactor());

    }

    @Override
    public Page<HotelPriceDTO> searchHotels(HotelSearchRequest hotelSearchRequest) {
        log.info("Searching hotels for {} city, from {} to {}", hotelSearchRequest.getCity(), hotelSearchRequest.getStartDate(), hotelSearchRequest.getEndDate());

        Pageable pageable = PageRequest.of(hotelSearchRequest.getPage(), hotelSearchRequest.getSize());
        long dateCount = ChronoUnit.DAYS.between(hotelSearchRequest.getStartDate(), hotelSearchRequest.getEndDate()) + 1;

        return hotelMinPriceRepository.findHotelsWithAvailableInventory(hotelSearchRequest.getCity(), hotelSearchRequest.getStartDate(), hotelSearchRequest.getEndDate(), hotelSearchRequest.getRoomsCount(), dateCount, pageable);

    }

    @Override
    public void deleteAllInventories(Room room) {
        log.info("Deleting all inventory for room with ID: {}", room.getId());
        inventoryRepository.deleteByRoom(room);
    }

}
