package africa.semicolon.myEcommerce2.exceptions;

public class InvalidTransferRequestException extends RuntimeException {
    public InvalidTransferRequestException(String message) {
        super(message);
    }
}
