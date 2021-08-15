package haoc.fiap.healthbackend.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseResponse {
    private String message;
    private Object response;
    private Integer httpCode;
}
