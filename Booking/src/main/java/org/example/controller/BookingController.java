package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.model.Bookings;
import org.example.service.BookingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookingController {

    final private BookingService bookingService;


    @PostMapping("/addBooking")
    public String addBooking(@Valid @RequestBody Bookings booking) throws JsonProcessingException {
           try {
               bookingService.addBooking(booking);
               return " your Booking is Confirmed";
           }catch (Exception e){
               return e.getMessage();
           }

    }

}
