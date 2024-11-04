package com.jn.bktravels.Model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EsewaPayment {

    private String amount;
    private String tax_amount;
    private String total_amount;
    private String transaction_uuid;
    private String product_code;
    private String product_service_charge;
    private String product_delivery_charge;
    private String success_url;
    private String failure_url;
    private String signed_field_names;
    private String signature;


    // You can add any additional fields or methods here if needed
}