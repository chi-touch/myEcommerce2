package africa.semicolon.myEcommerce2.mappers;

import africa.semicolon.myEcommerce2.data.model.Product;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;

public class ProductConverter implements Converter<Product,String> {
    @Override
    public String convert(Product product) {
        return null;
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }
}
