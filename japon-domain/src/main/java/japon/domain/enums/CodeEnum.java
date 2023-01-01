package japon.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public interface CodeEnum <E extends Enum<E>>{
    @JsonValue
    String getValue();

}
