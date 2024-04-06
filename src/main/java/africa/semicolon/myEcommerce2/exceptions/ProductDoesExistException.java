package africa.semicolon.myEcommerce2.exceptions;

public class ProductDoesExistException extends RuntimeException {
    public ProductDoesExistException(String message){
        super(message);
    }
}
