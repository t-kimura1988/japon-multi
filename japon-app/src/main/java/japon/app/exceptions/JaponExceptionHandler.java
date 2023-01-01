package japon.app.exceptions;

import japon.app.entity.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@Slf4j
@RestControllerAdvice
public class JaponExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<Object> handleJaponAlreadyExistException(
            JaponAlreadyExistException e,
            WebRequest request
    ) {
        return super.handleExceptionInternal(
                e,
                createErrResponse(HttpStatus.CONFLICT, e),
                null,
                HttpStatus.CONFLICT,
                request
        );
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleJaponIntegrityExistException(
            JaponIntegrityException e,
            WebRequest request
    ) {
        return super.handleExceptionInternal(
                e,
                createErrResponse(HttpStatus.CONFLICT, e),
                null,
                HttpStatus.CONFLICT,
                request
        );
    }

    private ErrorResponse createErrResponse(HttpStatus status, JaponException e) {
        Map<String, String> detail;
        detail = e.detail;
        JSONObject responseJson = new JSONObject(detail);

        return ErrorResponse.builder()
                .code(status.value())
                .title(e.title)
                .message(e.getMessage() + responseJson)
                .errorCd(e.errorCd)
                .build();
    }
}
