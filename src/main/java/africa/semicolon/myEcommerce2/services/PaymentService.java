package africa.semicolon.myEcommerce2.services;

import africa.semicolon.myEcommerce2.data.model.Payment;
import africa.semicolon.myEcommerce2.dto.request.PaymentAtDeliveryRequest;
import africa.semicolon.myEcommerce2.dto.request.TransferRequest;
import africa.semicolon.myEcommerce2.dto.response.PaymentDeliveryResponse;
import africa.semicolon.myEcommerce2.dto.response.TransferResponse;

public interface PaymentService {

    TransferResponse transfer(TransferRequest transferRequest);

    PaymentDeliveryResponse atDelivery(PaymentAtDeliveryRequest paymentAtDeliveryRequest);

}
