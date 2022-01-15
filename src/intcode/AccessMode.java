package intcode;

import java.security.InvalidParameterException;

public enum AccessMode {
    DIRECT,
    VALUE,
    INDIRECT;

    public static AccessMode fromCode(int code) {
        if(code == 0)
            return DIRECT;
        else if(code == 1)
            return VALUE;
        else if(code == 2)
            return INDIRECT;
        else
            throw new InvalidParameterException("Mode codes range from 0 to 2!");
    }
}
