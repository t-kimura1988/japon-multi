package japon.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.seasar.doma.Domain;

import java.util.Map;

@Slf4j
@AllArgsConstructor
@Domain(valueType = String.class, factoryMethod = "of")
public enum DelFlg implements CodeEnum<DelFlg> {
    NOT_DELETED("0", "未削除"),
    DELETED("1", "削除"),
    FIREBASE_DELETED("2", "Firebase連携済み");

    private final String value;
    @Getter
    private final String name;

    private static final Map<String, DelFlg> valueMap;

    static {
        valueMap = CodeUtil.initEnumMap(DelFlg.class);
    }

    public static DelFlg of(String value) { return valueMap.get(value); }

    @JsonCreator
    public static DelFlg fromString(String value) {
        return valueMap.get(value);
    }

    public String getValue() {return value;};
}
