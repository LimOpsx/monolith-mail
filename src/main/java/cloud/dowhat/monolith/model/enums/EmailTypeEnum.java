package cloud.dowhat.monolith.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author linen
 */
@AllArgsConstructor
@Getter
public enum EmailTypeEnum {

    TO(10, 0),
    CC(20, 1),
    BCC(30, 2);

    private final Integer code;

    private final Integer addressIndex;

    public static Integer getCodeByAddressIndex(Integer addressIndex) {
        for (EmailTypeEnum value : values()) {
            if (value.getAddressIndex().equals(addressIndex)) {
                return value.getCode();
            }
        }
        return null;
    }

}
