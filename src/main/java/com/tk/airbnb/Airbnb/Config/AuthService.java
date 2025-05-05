package com.tk.airbnb.Airbnb.Config;

import com.tk.airbnb.Airbnb.DTO.LoginDTO;
import com.tk.airbnb.Airbnb.DTO.SignUpRequestDTO;
import com.tk.airbnb.Airbnb.DTO.UserDTO;

public interface AuthService {
    UserDTO signUp(SignUpRequestDTO signUpRequestDTO);

    String[] login(LoginDTO loginDTO);

    String refreshToken(String refreshToken);
}
