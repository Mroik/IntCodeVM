package intcode.instructions;

import intcode.AccessMode;
import intcode.Memory;
import intcode.Registers;
import intcode.exceptions.InvalidMode;

/**
 * Class that implements the instruction for op code 02.
 * The instruction is a simple multiplication between the first 2 parameters,
 * the result is then written to the address specified by the 3rd parameter.
 */
public class Mult extends Instruction {
    public Mult(Memory memory, Registers registers) {
        super(memory, registers);
    }

    @Override
    public void execute(AccessMode[] modes) throws InvalidMode {
        int n1, n2, destinationAddress;
        n1 = readParameter(modes[0]);
        n2 = readParameter(modes[1]);
        destinationAddress = getWriteAddress(modes[2]);
        this.memory.write(destinationAddress, n1 * n2);
    }
}
