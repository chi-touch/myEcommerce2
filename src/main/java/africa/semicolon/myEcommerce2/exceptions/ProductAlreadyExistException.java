package africa.semicolon.myEcommerce2.exceptions;

public class ProductAlreadyExistException extends RuntimeException{
    public ProductAlreadyExistException(String messsage){
        super(messsage);
    }
}
