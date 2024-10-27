package com.jn.bktravels.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "contacts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @ManyToOne
    @JoinColumn(insertable = false, name = "user_id", nullable = false, updatable = false)
    private User user;
}
