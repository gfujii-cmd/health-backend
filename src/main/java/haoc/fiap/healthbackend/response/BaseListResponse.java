package haoc.fiap.healthbackend.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BaseListResponse<T> {
    private Integer httpCode;
    private List<T> data;
    private String error;
}
