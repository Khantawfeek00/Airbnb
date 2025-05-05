package com.tk.airbnb.Airbnb.Repository;


import com.tk.airbnb.Airbnb.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
