package com.tk.airbnb.Airbnb.DTO;

import lombok.Data;

@Data
public class SignUpRequestDTO {
    private String email;
    private String password;
    private String name;
}
