package org.example.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import jakarta.persistence.*;


@Data
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Table(name = "tables")
public class Tables {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tableId;

    @Column(nullable = false)
    private String restaurantUsername;

    @Column(nullable = false)
    private long tableNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = true)
    private String bookingId;


    public enum Status {
        AVAILABLE,
        BOOKED
    }
}