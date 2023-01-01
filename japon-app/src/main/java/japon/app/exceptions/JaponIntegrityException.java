package japon.app.exceptions;

import java.util.Map;

public class JaponIntegrityException extends JaponException{

    public JaponIntegrityException(String message, Map<String, String> param) {
        super(message, param);
    }

    public JaponIntegrityException(String title, String message, Map<String, String> param) {
        super(title, message, param);
    }
    public JaponIntegrityException(String title, String message, Map<String, String> param, String errorCd) {
        super(title, message, param, errorCd);
    }
}
