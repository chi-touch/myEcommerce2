package africa.semicolon.myEcommerce2.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Data
@AllArgsConstructor
public class ApiResponse {

    private boolean isSuccess;
    private Object object;
}
