package com.tk.airbnb.Airbnb.Controller;

import com.tk.airbnb.Airbnb.DTO.HotelInfoDTO;
import com.tk.airbnb.Airbnb.DTO.HotelPriceDTO;
import com.tk.airbnb.Airbnb.DTO.HotelSearchRequest;
import com.tk.airbnb.Airbnb.Service.Interfaces.HotelService;
import com.tk.airbnb.Airbnb.Service.Interfaces.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotels")
public class HotelBrowseController {

    private final InventoryService inventoryService;
    private final HotelService hotelService;

    @GetMapping("/search")
    public ResponseEntity<Page<HotelPriceDTO>> searchHotels(@RequestBody HotelSearchRequest hotelSearchRequest) {
        Page<HotelPriceDTO> hotelPricePage = inventoryService.searchHotels(hotelSearchRequest);
        return ResponseEntity.ok(hotelPricePage);
    }

    @GetMapping("/{hotelId}/info")
    public ResponseEntity<HotelInfoDTO> getHotelInfo(@PathVariable Long hotelId) {
        HotelInfoDTO hotelInfoDTO = hotelService.getHotelInfoById(hotelId);
        return ResponseEntity.ok(hotelInfoDTO);
    }
}
