package com.tk.airbnb.Airbnb.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingPaymentInitResponseDTO {
    private String sessionUrl;
}
