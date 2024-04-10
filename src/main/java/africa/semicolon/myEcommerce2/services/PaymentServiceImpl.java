package africa.semicolon.myEcommerce2.services;

import africa.semicolon.myEcommerce2.data.model.Payment;
import africa.semicolon.myEcommerce2.data.repositories.PaymentRepository;
import africa.semicolon.myEcommerce2.dto.request.PaymentAtDeliveryRequest;
import africa.semicolon.myEcommerce2.dto.request.TransferRequest;
import africa.semicolon.myEcommerce2.dto.request.WithdrawRequest;
import africa.semicolon.myEcommerce2.dto.response.TransferResponse;
import africa.semicolon.myEcommerce2.exceptions.InvalidPaymentRequestException;
import africa.semicolon.myEcommerce2.exceptions.InvalidTransferRequestException;
import africa.semicolon.myEcommerce2.exceptions.NegativeAmountException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static africa.semicolon.myEcommerce2.data.model.TransactionStatus.PENDING;
import static africa.semicolon.myEcommerce2.data.model.TransactionStatus.SUCCESS;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;

    @Override
    public TransferResponse transfer(TransferRequest transferRequest) {
        BigDecimal amount = transferRequest.getAmount();
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new NegativeAmountException("Amount should be positive");
        }

        if (invalidTransferRequest(transferRequest)) {
            throw new InvalidTransferRequestException("Invalid transfer request");
        }

        boolean transferSuccess = performTransfer(transferRequest);

        Payment payment = new Payment();
        payment.setBalance(transferRequest.getAmount());
        payment.setAccountNumber(transferRequest.getTo());
        payment.setAmount(transferRequest.getAmount());
        payment.setStatus(SUCCESS);
        paymentRepository.save(payment);
        TransferResponse response = new TransferResponse();
        if (transferSuccess) {
            response.setMessage("Transfer successful");
            response.setAmountPaid(transferRequest.getAmount());
        } else {
            response.setMessage("Transfer failed");
            response.setAmountPaid(BigDecimal.ZERO);
        }
        return response;
    }

    private boolean invalidTransferRequest(TransferRequest transferRequest) {
        return transferRequest.getFrom() == null ||
                transferRequest.getTo() == null ||
                transferRequest.getAmount() == null ||
                transferRequest.getPin() == null ||
                transferRequest.getAmount().compareTo(BigDecimal.ZERO) <= 0;
    }

    private boolean performTransfer(TransferRequest transferRequest) {
        System.out.println("Transferring " + transferRequest.getAmount() + " from " + transferRequest.getFrom() +
                " to " + transferRequest.getTo() + " with description: " + transferRequest.getDescription());
        return true;
    }

    @Override
    public Payment atDelivery(PaymentAtDeliveryRequest paymentAtDeliveryRequest) {
        if (paymentAtDeliveryRequest == null) {
            throw new InvalidPaymentRequestException("Payment request is null");
        }
        if (paymentAtDeliveryRequest.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidPaymentRequestException("Payment amount should be greater than zero");
        }
        if (paymentAtDeliveryRequest.getDeliveryId() == null || paymentAtDeliveryRequest.getDeliveryId().isEmpty()) {
            throw new InvalidPaymentRequestException("Delivery ID is required");
        }
        Payment payment = new Payment();
        payment.setAmount(paymentAtDeliveryRequest.getAmount());
        payment.setDeliveryId(paymentAtDeliveryRequest.getDeliveryId());
        payment.setStatus(PENDING);
        return payment;
    }

}
