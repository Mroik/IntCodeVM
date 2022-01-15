package intcode.instructions;

import intcode.AccessMode;
import intcode.Memory;
import intcode.Registers;
import intcode.exceptions.InvalidMode;

/**
 * The class that implements the instruction for op code 01.
 * This is a simple add where the first 2 parameters are added
 * together and end up in the 3rd parameter.
 */
public class Add extends Instruction {
    public Add(Memory memory, Registers registers) {
        super(memory, registers);
    }

    @Override
    public void execute(AccessMode[] modes) throws InvalidMode {
        int n1, n2, destinationAddress;
        n1 = readParameter(modes[0]);
        n2 = readParameter(modes[1]);
        destinationAddress = getWriteAddress(modes[2]);
        this.memory.write(destinationAddress, n1 + n2);
    }
}
