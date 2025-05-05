package com.tk.airbnb.Airbnb.Repository;

import com.tk.airbnb.Airbnb.Model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    
    @Query(value = "SELECT * FROM rooms WHERE hotel_id = :hotelId AND room_id = :roomId", nativeQuery = true)
    Optional<Room> findByHotelIdAndRoomId(@Param("hotelId") Long hotelId, @Param("roomId") Long roomId);
}
