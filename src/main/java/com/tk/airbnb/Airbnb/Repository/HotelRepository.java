package com.tk.airbnb.Airbnb.Repository;

import com.tk.airbnb.Airbnb.Model.Hotel;
import com.tk.airbnb.Airbnb.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long> {
    List<Hotel> findByOwner(User user);
}

