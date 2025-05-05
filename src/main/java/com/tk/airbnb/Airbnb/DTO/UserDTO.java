package com.tk.airbnb.Airbnb.DTO;

import com.tk.airbnb.Airbnb.Enum.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private Gender gender;
    private LocalDate dateOfBirth;
}
