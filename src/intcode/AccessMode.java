package intcode;

import java.security.InvalidParameterException;

/**
 * Constants representing the various AccessModes available to the parameters.
 * POSITION - The address where the value should be read from, or should be written to.
 * IMMEDIATE - Not used in write operations, if a parameter is IMMEDIATE itself is the value.
 * RELATIVE - Like POSITION but instead of having directly an address the parameter should be added to the RBP
 * to see what the actual address is.
 */
public enum AccessMode {
    POSITION,
    IMMEDIATE,
    RELATIVE;

    public static AccessMode fromCode(int code) {
        if(code == 0)
            return POSITION;
        else if(code == 1)
            return IMMEDIATE;
        else if(code == 2)
            return RELATIVE;
        else
            throw new InvalidParameterException("Mode codes range from 0 to 2!");
    }
}
