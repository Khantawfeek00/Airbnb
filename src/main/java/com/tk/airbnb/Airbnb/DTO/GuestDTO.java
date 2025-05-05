package com.tk.airbnb.Airbnb.DTO;

import com.tk.airbnb.Airbnb.Enum.Gender;
import lombok.Data;

@Data
public class GuestDTO {
    private Long id;
    private String name;
    private Gender gender;
    private Integer age;
}
