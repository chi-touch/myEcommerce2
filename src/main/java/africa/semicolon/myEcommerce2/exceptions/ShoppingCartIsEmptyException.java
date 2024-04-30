package africa.semicolon.myEcommerce2.exceptions;

public class ShoppingCartIsEmptyException extends RuntimeException {
    public ShoppingCartIsEmptyException(String message) {
        super(message);
    }
}
