package japon.app.exceptions;

import lombok.Builder;

import java.util.Map;

public class JaponAlreadyExistException extends JaponException{

    public String getTitle() {
        return super.title;
    }

    public String getErrorCd() {
        return super.errorCd;
    }

    public Map<String, String> getDetail() {
        return super.detail;
    }

    public JaponAlreadyExistException(String message, Map<String, String> param) {
        super(message, param);
    }

    public JaponAlreadyExistException(String title, String message, Map<String, String> param) {
        super(title, message, param);
    }
}
