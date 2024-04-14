package africa.semicolon.myEcommerce2.controllers;

import africa.semicolon.myEcommerce2.dto.request.PaymentAtDeliveryRequest;
import africa.semicolon.myEcommerce2.dto.request.TransferRequest;
import africa.semicolon.myEcommerce2.dto.response.PaymentDeliveryResponse;
import africa.semicolon.myEcommerce2.dto.response.TransferResponse;
import africa.semicolon.myEcommerce2.services.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/payments")
@AllArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("transfer")
    public ResponseEntity<TransferResponse> transfer(@RequestBody TransferRequest transferRequest){
        TransferResponse response = paymentService.transfer(transferRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PostMapping("/atDelivery")
    public ResponseEntity<PaymentDeliveryResponse> atDelivery(@RequestBody PaymentAtDeliveryRequest paymentAtDeliveryRequest){
        PaymentDeliveryResponse response = paymentService.atDelivery(paymentAtDeliveryRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
