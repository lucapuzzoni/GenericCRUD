package it.lucapuzzoni.crud.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author Luca Puzzoni
 * @param <T> Type of the payload
 * Standard response from CRUD APIs
 */
@Data @Builder
public class CrudResponse<T> {
    private HttpStatus status;
    private String code;
    private T payload;
}
