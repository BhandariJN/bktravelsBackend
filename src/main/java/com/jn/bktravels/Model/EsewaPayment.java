package com.bktravels.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "esewa_payments")
public class EsewaPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String amount;
    private String failure_url;
    private String product_delivery_charge;
    private String product_service_charge;
    private String product_code;
    private String signature;
    private String signed_field_names;
    private String success_url;
    private String tax_amount;
    private String total_amount;
    private String transaction_uuid;
}
