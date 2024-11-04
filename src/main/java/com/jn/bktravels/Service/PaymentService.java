package com.jn.bktravels.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jn.bktravels.Config.MerchantKey;
import com.jn.bktravels.Config.SecurityConfig;
import com.jn.bktravels.Config.Signature;
import com.jn.bktravels.Model.Booking;
import com.jn.bktravels.Model.EsewaPayment;
import com.jn.bktravels.Repository.BookingRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final BookingRepo bookingRepo;
    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    public String paymentGenerator(float totalAmount, String transactionUuid) {
        Signature signature = new Signature();
        return signature.secretHashGenerator(totalAmount, transactionUuid);
    }

    public EsewaPayment paymentService(int id) {
        Optional<Booking> bookingOptional = bookingRepo.findById((long) id);

        if (bookingOptional.isPresent()) {
            Booking booking = bookingOptional.get();

            // UUID generation
            String transactionUuid = UUID.randomUUID().toString();
            logger.info("Generated transaction UUID: {}", transactionUuid);

            // Generate signature
            String generatedSignature = paymentGenerator((float) booking.getTotalAmount(), transactionUuid);
            SecurityConfig config = new SecurityConfig();
            MerchantKey merchantKey = new MerchantKey();

            String secretKey = config.getSecretKey();
            String merchantId = merchantKey.getMerchantKey();
            String signedFieldNames = "total_amount,transaction_uuid,product_code";

            // Create and populate EsewaPayment instance
            EsewaPayment esewaPayment = EsewaPayment.builder()
                    .total_amount(String.valueOf(booking.getTotalAmount()))
                    .failure_url("http://localhost:8080/booking/payment-failure")
                    .product_delivery_charge("0") // or actual delivery charge
                    .product_service_charge("0") // or actual service charge
                    .product_code(merchantId)
                    .signature(generatedSignature)
                    .signed_field_names(signedFieldNames) // list of signed fields
                    .success_url("http://localhost:8080/booking/payment-success/"+booking.getId())
                    .tax_amount("0") // or actual tax amount
                    .amount(String.valueOf(booking.getTotalAmount()))
                    .transaction_uuid(transactionUuid)
                    .build();
            logger.info("Created EsewaPayment: {}", esewaPayment);
            return esewaPayment;
        } else {
            logger.error("Booking not found for ID: {}", id);
            throw new IllegalArgumentException("Booking not found for ID: " + id);
        }
    }

    public RedirectView updatePayment(String encodedData, String id) throws IOException {
            // Step 1: Decode the base64 encoded data
            byte[] decodedBytes = Base64.getDecoder().decode(encodedData);
            String decodedJson = new String(decodedBytes);

            // Step 2: Parse JSON data
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(decodedJson);

            // Step 3: Extract required fields
            String transactionCode = jsonNode.get("transaction_code").asText();
            String status = jsonNode.get("status").asText();
            String totalAmount = jsonNode.get("total_amount").asText();
            String transactionUuid = jsonNode.get("transaction_uuid").asText();
            String productCode = jsonNode.get("product_code").asText();

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:5500/viewownbooking.html");
            if ("COMPLETED".equals(status)) {

                Optional<Booking> booking = bookingRepo.findById((long) Long.parseLong(id));
                if (booking.isPresent()) {



                    bookingRepo.updateBookingStatus(Long.valueOf(id), Booking.Status.PAID.name());

                     // Redirect to a confirmation page
                    return redirectView;
                }
            }
            return  redirectView;
    }
}
