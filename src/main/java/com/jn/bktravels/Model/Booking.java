package com.jn.bktravels.Model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "bookings")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private  Integer numberofTravellers;


    @Column(nullable = false)
    private double totalAmount;

    @Column(nullable = false)
    private LocalDateTime bookedDate;

    @Column(nullable = false)
    private LocalDateTime selectedDate;


    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    public enum Status {
        PENDING,
        PAID,
        CANCELLED
    }

    @ManyToOne
    @JoinColumn(name = "user_id",
            nullable = false
    )
    private User user;




}
