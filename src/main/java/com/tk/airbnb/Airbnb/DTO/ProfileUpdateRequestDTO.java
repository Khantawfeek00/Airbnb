package com.tk.airbnb.Airbnb.DTO;

import com.tk.airbnb.Airbnb.Enum.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProfileUpdateRequestDTO {
    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;
}
