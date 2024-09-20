package org.example.repository;

import org.example.model.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Bookings,Long> {
}
