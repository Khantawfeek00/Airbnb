package com.tk.airbnb.Airbnb.Service.Interfaces;


import com.tk.airbnb.Airbnb.Model.Booking;

public interface CheckoutService {

    String getCheckoutSession(Booking booking, String successUrl, String failureUrl);

}
