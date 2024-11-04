package com.jn.bktravels.Model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "destinations")
@Getter
@Setter

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String destinationName;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String destinationDescription;
    @Column(nullable = false)
    private Double destinationPrice;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String imageUrl;

    @ElementCollection
    @CollectionTable(name = "destination_availability", joinColumns = @JoinColumn(name = "destination_id"))
    @Column(name = "available_date")
    private List<LocalDateTime> availability;


}
