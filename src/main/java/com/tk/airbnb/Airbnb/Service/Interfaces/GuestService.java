package com.tk.airbnb.Airbnb.Service.Interfaces;

import com.tk.airbnb.Airbnb.DTO.GuestDTO;

import java.util.List;

public interface GuestService {
    List<GuestDTO> getAllGuests();

    GuestDTO addNewGuest(GuestDTO guestDTO);

    GuestDTO updateGuest(Long guestId, GuestDTO guestDTO);

    void deleteGuestById(Long guestId);
}
