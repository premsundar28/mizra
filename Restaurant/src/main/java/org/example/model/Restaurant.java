package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "restaurants")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurants_seq")
    @SequenceGenerator(name = "restaurants_seq", sequenceName = "restaurants_seq", allocationSize = 50)
    private long id;

    @Column(name = "restaurant_name", unique = true, nullable = false)
    private String restaurantName;

    @Column(name = "house_no")
    private String houseNo;

    @Column(name = "locality")
    private String locality;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "description")
    private String description;

    @Column(name = "owner_name")
    private String ownerName;

    @Column(name = "phone_number", unique = true, nullable = true)
    private String phoneNumber;

    @Column(name = "email", unique = true, nullable = true)
    private String email;

    @Column(name = "opening_time")
    private LocalTime openingTime;

    @Column(name = "closing_time")
    private LocalTime closingTime;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Menu> menu;
}