package haoc.fiap.healthbackend.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseResponse<T> {
    private String message;
    private T response;
    private Integer httpCode;
}
