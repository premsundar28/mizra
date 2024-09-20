package org.example.model;

import jakarta.persistence.*;
import lombok.*;


import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String userId;

    private String name;
    private String email;
    private String phone;

    private String authorities;
    private String password;


}