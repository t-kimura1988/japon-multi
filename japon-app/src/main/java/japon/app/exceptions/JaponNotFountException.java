package japon.app.exceptions;

import lombok.Builder;

import java.util.Map;

public class JaponNotFountException extends JaponException{

    public JaponNotFountException(String message, Map<String, String> param) {
        super(message, param);
    }

    public JaponNotFountException(String title, String message, Map<String, String> param) {
        super(title, message, param);
    }
}
