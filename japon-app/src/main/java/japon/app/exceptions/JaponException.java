package japon.app.exceptions;

import lombok.Builder;

import java.util.Map;

public class JaponException extends Exception{
    String title;
    Map<String, String> detail;
    String errorCd;

    public JaponException(String message, Map<String, String> param) {
        super(message);
        this.detail = param;
    }
    public JaponException(String title, String message, Map<String, String> param) {
        super(message);
        this.detail = param;
        this.title = title;
    }
    public JaponException(String title, String message, Map<String, String> param, String errorCd) {
        super(message);
        this.detail = param;
        this.title = title;
        this.errorCd = errorCd;
    }
}
