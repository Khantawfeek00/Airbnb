package com.tk.airbnb.Airbnb.Controller;

import com.tk.airbnb.Airbnb.DTO.BookingDTO;
import com.tk.airbnb.Airbnb.DTO.GuestDTO;
import com.tk.airbnb.Airbnb.DTO.ProfileUpdateRequestDTO;
import com.tk.airbnb.Airbnb.DTO.UserDTO;
import com.tk.airbnb.Airbnb.Service.Interfaces.BookingService;
import com.tk.airbnb.Airbnb.Service.Interfaces.GuestService;
import com.tk.airbnb.Airbnb.Service.Interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@SecurityRequirement(name = "BearerAuth")
@Tag(name = "User Profile", description = "Manage user profiles and bookings")
public class UserController {

    private final UserService userService;
    private final BookingService bookingService;
    private final GuestService guestService;

    @PatchMapping("/profile")
    @Operation(summary = "Update my profile", description = "Allows a user to update their profile details.", tags = {"User Profile"})
    public ResponseEntity<Void> updateProfile(ProfileUpdateRequestDTO profileUpdateRequestDto) {
        userService.updateProfile(profileUpdateRequestDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/myBookings")
    @Operation(summary = "Get my bookings", description = "Fetches a list of all past bookings for the user.", tags = {"User Profile"})
    public ResponseEntity<List<BookingDTO>> getMyBookings() {
        return ResponseEntity.ok(bookingService.getMyBookings());
    }

    @GetMapping("/profile")
    @Operation(summary = "Get my profile", description = "Retrieves the current user's profile details.", tags = {"User Profile"})
    public ResponseEntity<UserDTO> getMyProfile() {
        return ResponseEntity.ok(userService.getMyProfile());
    }

    @GetMapping("/guests")
    @Operation(summary = "Get my guests", description = "Retrieves a list of guests associated with the user's bookings.", tags = {"Booking Guests"})
    public ResponseEntity<List<GuestDTO>> getAllGuests() {
        return ResponseEntity.ok(guestService.getAllGuests());
    }

    @PostMapping("/guests")
    @Operation(summary = "Add a guest", description = "Adds a new guest to the user's guest list.", tags = {"Booking Guests"})
    public ResponseEntity<GuestDTO> addNewGuest(@RequestBody GuestDTO guestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(guestService.addNewGuest(guestDTO));
    }

    @PutMapping("/guests/{guestId}")
    @Operation(summary = "Update a guest", description = "Updates details of a guest in the user's guest list.", tags = {"Booking Guests"})
    public ResponseEntity<GuestDTO> updateGuest(@PathVariable Long guestId, @RequestBody GuestDTO guestDTO) {
        return ResponseEntity.ok(guestService.updateGuest(guestId, guestDTO));
    }

    @DeleteMapping("/guests/{guestId}")
    @Operation(summary = "Remove a guest", description = "Removes a guest from the user's guest list.", tags = {"Booking Guests"})
    public ResponseEntity<Void> deleteGuest(@PathVariable Long guestId) {
        guestService.deleteGuestById(guestId);
        return ResponseEntity.noContent().build();
    }


}
