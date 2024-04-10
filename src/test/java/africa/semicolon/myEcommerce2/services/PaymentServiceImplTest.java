package africa.semicolon.myEcommerce2.services;

import africa.semicolon.myEcommerce2.dto.request.TransferRequest;
import africa.semicolon.myEcommerce2.dto.response.TransferResponse;
import africa.semicolon.myEcommerce2.exceptions.InvalidTransferAmountException;
import africa.semicolon.myEcommerce2.exceptions.NegativeAmountException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PaymentServiceImplTest {

    @Autowired
    PaymentService paymentService;
    @Test
    public void testTransfer_Successful() {
        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setFrom("Sender");
        transferRequest.setTo("Receiver");
        transferRequest.setAmount(BigDecimal.valueOf(100));
        transferRequest.setPin("1234");

        TransferResponse response = paymentService.transfer(transferRequest);
        assertEquals("Transfer successful", response.getMessage());
        assertEquals(BigDecimal.valueOf(100), response.getAmountPaid());
    }

    @Test
    public void testToMakeAnotherTransfer(){
        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setFrom("Sender");
        transferRequest.setTo("Receiver");
        transferRequest.setAmount(BigDecimal.valueOf(100));
        transferRequest.setPin("1234");


        TransferResponse transferResponse =paymentService.transfer(transferRequest);

        assertEquals("Transfer successful", transferResponse.getMessage());
        assertEquals(BigDecimal.valueOf(100), transferResponse.getAmountPaid());

    }

    @Test
    public void testNegativeAmountCanNotBeTransferred(){
        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setFrom("Sender");
        transferRequest.setTo("Receiver");
        transferRequest.setAmount(BigDecimal.valueOf(-3000));
        transferRequest.setPin("1234");
        assertThrows(NegativeAmountException.class,() -> paymentService.transfer(transferRequest));

    }




}