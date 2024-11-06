package com.jn.bktravels.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jn.bktravels.Config.MerchantKey;
import com.jn.bktravels.Config.SecurityConfig;
import com.jn.bktravels.Config.Signature;
import com.jn.bktravels.Model.Booking;
import com.jn.bktravels.Model.EsewaPayment;
import com.jn.bktravels.Repository.BookingRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
            System.out.println(esewaPayment.toString());
            return esewaPayment;
        } else {
            logger.error("Booking not found for ID: {}", id);
            throw new IllegalArgumentException("Booking not found for ID: " + id);
        }
    }

    @Transactional
    public RedirectView updatePayment(String encodedData, Long bookingId) throws IOException {
        System.out.println("Encoded Data: " + encodedData);

        // Step 1: Decode the base64 encoded data
        byte[] decodedBytes = Base64.getDecoder().decode(encodedData);
        String decodedJson = new String(decodedBytes);
        System.out.println("Decoded JSON: " + decodedJson);

        // Step 2: Parse JSON data
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(decodedJson);

        // Step 3: Extract and validate required fields
        String transactionCode = jsonNode.has("transaction_code") ? jsonNode.get("transaction_code").asText() : null;
        String status = jsonNode.has("status") ? jsonNode.get("status").asText() : null;
        String totalAmount = jsonNode.has("total_amount") ? jsonNode.get("total_amount").asText() : null;
        String transactionUuid = jsonNode.has("transaction_uuid") ? jsonNode.get("transaction_uuid").asText() : null;
        String productCode = jsonNode.has("product_code") ? jsonNode.get("product_code").asText() : null;

        // Define redirect URL
        RedirectView redirectView = new RedirectView();


        if ("COMPLETE".equals(status)) {
            Optional<Booking> bookingOptional = bookingRepo.findById(bookingId);
            if (bookingOptional.isPresent()) {
                Booking booking = bookingOptional.get();
                bookingRepo.updateBookingStatus(booking.getId(), Booking.Status.CONFIRMED, transactionUuid);
                System.out.println("Payment completed for booking ID: " + bookingId);

                // Set success URL
                redirectView.setUrl("http://localhost/bktravelfrontend/user-view/viewownbooking.html");
                return redirectView;
            } else {
                System.out.println("Booking not found for ID: " + bookingId);
                redirectView.setUrl("http://localhost/bktravelfrontend/user-view/viewownbooking.html");
            }
        } else {
            System.out.println("Payment status not complete for booking ID: " + bookingId);
            redirectView.setUrl("http://localhost/bktravelfrontend/user-view/viewownbooking.html");
        }

         redirectView.setUrl("http://localhost/bktravelfrontend/user-view/viewownbooking.html");
        return redirectView;
    }

}
