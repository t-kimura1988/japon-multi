package japon.app.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorResponse {
    Integer code;
    String title;
    String message;
    String errorCd;
}
