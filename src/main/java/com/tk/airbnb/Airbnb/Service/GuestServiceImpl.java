package com.tk.airbnb.Airbnb.Service;

import com.tk.airbnb.Airbnb.DTO.GuestDTO;
import com.tk.airbnb.Airbnb.Exception.ResourceNotFoundException;
import com.tk.airbnb.Airbnb.Model.Guest;
import com.tk.airbnb.Airbnb.Model.User;
import com.tk.airbnb.Airbnb.Repository.GuestRepository;
import com.tk.airbnb.Airbnb.Service.Interfaces.GuestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.tk.airbnb.Airbnb.Util.AppUtils.getCurrentUser;

@Service
@RequiredArgsConstructor
@Slf4j
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<GuestDTO> getAllGuests() {
        User user = getCurrentUser();
        log.info("Fetching all guests of user with id: {}", user.getId());
        List<GuestDTO> guestDTOList = guestRepository.findByUser(user);
        return guestDTOList.stream().map(guest -> modelMapper.map(guest, GuestDTO.class)).collect(Collectors.toList());
    }

    @Override
    public GuestDTO addNewGuest(GuestDTO guestDTO) {
        log.info("Adding new Guest: {}", guestDTO.getName());
        User user = getCurrentUser();
        Guest guest = modelMapper.map(guestDTO, Guest.class);
        guest.setUser(user);
        Guest savedGuest = guestRepository.save(guest);
        log.info("Added new Guest with id: {}", savedGuest.getId());
        return modelMapper.map(savedGuest, GuestDTO.class);
    }

    @Override
    public GuestDTO updateGuest(Long guestId, GuestDTO guestDTO) {
        log.info("Updating Guest with id: {}", guestId);

        Guest guest = guestRepository.findById(guestId).orElseThrow(() -> new ResourceNotFoundException("Guest not found"));

        User user = getCurrentUser();
        if (!user.equals(guest.getUser())) {
            throw new AccessDeniedException("You do not have permission to update this guest");
        }

        Guest newGuest = modelMapper.map(guestDTO, Guest.class);
        newGuest.setId(guestId);
        newGuest.setUser(user);
        Guest updatedGuest = guestRepository.save(newGuest);
        log.info("Updated Guest with id: {}", updatedGuest.getId());
        return modelMapper.map(updatedGuest, GuestDTO.class);
    }

    @Override
    public void deleteGuestById(Long guestId) {
        log.info("Deleting Guest with id: {}", guestId);

        Guest guest = guestRepository.findById(guestId).orElseThrow(() -> new ResourceNotFoundException("Guest not found"));

        User user = getCurrentUser();
        if (!user.equals(guest.getUser())) {
            throw new AccessDeniedException("You do not have permission to delete this guest");
        }
        guestRepository.deleteById(guestId);
        log.info("Deleted Guest with id: {}", guestId);
    }
}
