package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.bson.types.ObjectId;
import org.example.model.Bookings;
import org.example.model.Tables;
import org.example.repository.BookingCacheRepository;
import org.example.repository.BookingRepository;
import org.example.repository.TablesRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingService {

    private static final String BOOKING_CREATE_TOPIC = "booking_create";

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    final private BookingCacheRepository bookingCacheRepository;

    final private BookingRepository bookingRepository;

    final private TablesRepository tableRepository;

    public void addBooking(Bookings booking) throws JsonProcessingException {
        Tables table;
        try {
            // Fetch the table by table number
            table = tableRepository.findByTableNumber(booking.getTableId());

            if (table == null) {
                throw new IllegalArgumentException("Table not found");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while fetching table: " + e.getMessage(), e);
        }

        // Check if the table is available
        if (table.getStatus() != Tables.Status.AVAILABLE) {
            throw new IllegalStateException("Table is not available for booking");
        }

        // Save the booking
        bookingRepository.save(booking);

        // Update the table status to 'BOOKED' and save it
        table.setStatus(Tables.Status.BOOKED);
        tableRepository.save(table);
    }

}


