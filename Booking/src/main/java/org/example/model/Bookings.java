package org.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "bookings")
public class Bookings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Auto-generated ID

    @Column(nullable = false)
    private String customer;

    @Column(nullable = false)
    private String restaurantUsername;

    @Column(nullable = false)
    private long tableId;

    @Column(nullable = false)
    private ZonedDateTime bookingTime = ZonedDateTime.now(); // Auto-set booking time

    @ElementCollection
    @CollectionTable(name = "booking_order", joinColumns = @JoinColumn(name = "booking_id"))
    private List<OrderItem> order;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    private String specialRequests;

    @Data
    @NoArgsConstructor
    @Embeddable
    public static class OrderItem {
        private String name;
        private int quantity;
        private String specialInstructions;
    }

    public enum Status {
        PENDING,
        ACCEPTED,
        REJECTED,
        IN_PREPARATION,
        COMPLETED
    }
}