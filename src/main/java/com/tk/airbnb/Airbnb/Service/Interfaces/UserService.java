package com.tk.airbnb.Airbnb.Service.Interfaces;

import com.tk.airbnb.Airbnb.DTO.ProfileUpdateRequestDTO;
import com.tk.airbnb.Airbnb.DTO.UserDTO;
import com.tk.airbnb.Airbnb.Model.User;

public interface UserService {
    User getUserById(Long id);

    void updateProfile(ProfileUpdateRequestDTO profileUpdateRequestDto);

    UserDTO getMyProfile();
}
