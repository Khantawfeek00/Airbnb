package com.tk.airbnb.Airbnb.Service.Interfaces;

import com.stripe.model.Event;
import com.tk.airbnb.Airbnb.DTO.BookingDTO;
import com.tk.airbnb.Airbnb.DTO.BookingRequest;
import com.tk.airbnb.Airbnb.DTO.GuestDTO;
import com.tk.airbnb.Airbnb.DTO.HotelReportDTO;
import com.tk.airbnb.Airbnb.Enum.BookingStatus;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    List<BookingDTO> getMyBookings();

    List<BookingDTO> getAllBookingsByHotelId(Long hotelId);

    HotelReportDTO getHotelReport(Long hotelId, LocalDate startDate, LocalDate endDate);

    BookingDTO initialiseBooking(BookingRequest bookingRequest);

    BookingDTO addGuests(Long bookingId, List<GuestDTO> guestDtoList);

    String initiatePayments(Long bookingId);

    void cancelBooking(Long bookingId);

    BookingStatus getBookingStatus(Long bookingId);

    void capturePayment(Event event);
}
