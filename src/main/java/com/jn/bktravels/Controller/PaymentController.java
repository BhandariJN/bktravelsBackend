package com.jn.bktravels.Controller;

import com.jn.bktravels.Service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@Controller
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @CrossOrigin
    @GetMapping("/booking/{id}/pay")
    public ResponseEntity<?> paymentGenerator(@PathVariable int id) {
        return new ResponseEntity<>(paymentService.paymentService(id), HttpStatus.OK);
    }


    @GetMapping("/booking/payment-success/{id}")
    public RedirectView handlePaymentSuccess(@RequestParam("data") String encodedData, @PathVariable String id) throws IOException {
       return paymentService.updatePayment(encodedData, id);

    }
    @GetMapping("/booking/payment-failure")
    public RedirectView handlePaymentFailure() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:5500/viewownbooking.html"); // Redirect to a confirmation page
        return redirectView;
    }

}
