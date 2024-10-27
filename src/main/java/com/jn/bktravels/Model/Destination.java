package com.jn.bktravels.Model;


import jakarta.persistence.*;
import lombok.*;

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
    @Column(nullable = false)
    private String imageName;
    @Column(nullable = false)
    private String imageType;

    @Lob
    @Column(nullable = false, columnDefinition = "LONGBLOB")
    private byte[] imageData;






}
