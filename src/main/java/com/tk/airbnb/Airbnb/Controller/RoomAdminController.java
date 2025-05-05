package com.tk.airbnb.Airbnb.Controller;

import com.tk.airbnb.Airbnb.DTO.RoomDTO;
import com.tk.airbnb.Airbnb.Service.Interfaces.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/hotels/{hotelId}/rooms")
@SecurityRequirement(name = "BearerAuth")
@Tag(name = "Room Admin Management", description = "Admin Manage rooms/hotels/inventories in a hotel")
public class RoomAdminController {

    private final RoomService roomService;

    @PostMapping
    @Operation(summary = "Create a new room",
            description = "Adds a new room to a specific hotel")
    public ResponseEntity<RoomDTO> createNewRoom(@PathVariable Long hotelId, @PathVariable RoomDTO roomDTO) {
        RoomDTO room = roomService.createNewRoom(hotelId, roomDTO);
        return new ResponseEntity<>(room, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Retrieve all rooms in a hotel",
            description = "Fetches all rooms belonging to the specified hotel")
    public ResponseEntity<List<RoomDTO>> getAllRoomsInHotel(@PathVariable Long hotelId) {
        List<RoomDTO> rooms = roomService.getAllRoomdsInHotel(hotelId);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/{roomId}")
    @Operation(summary = "Get details of a specific room",
            description = "Fetches details of a specific room in a hotel by ID")
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable Long hotelId, @PathVariable Long roomId) {
        RoomDTO room = roomService.getRoomById(hotelId, roomId);
        return ResponseEntity.ok(room);
    }

    @DeleteMapping
    @Operation(summary = "Delete a room",
            description = "Deletes a room from the hotel by ID")
    public ResponseEntity<RoomDTO> deleteRoomById(@PathVariable Long hotelId, @PathVariable Long roomId) {
        roomService.deleteRoomById(hotelId, roomId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Operation(summary = "Update a room",
            description = "Updates the details of an existing room", tags = {"Admin Inventory"})
    public ResponseEntity<RoomDTO> updateRoomById(@PathVariable Long hotelId, @PathVariable Long roomId, @RequestBody RoomDTO roomDTO) {
        RoomDTO room = roomService.updateRoomById(hotelId, roomId, roomDTO);
        return ResponseEntity.ok(room);
    }
}
