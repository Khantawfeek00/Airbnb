package com.tk.airbnb.Airbnb.Repository;

import com.tk.airbnb.Airbnb.DTO.GuestDTO;
import com.tk.airbnb.Airbnb.Model.Guest;
import com.tk.airbnb.Airbnb.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
    List<GuestDTO> findByUser(User user);
}