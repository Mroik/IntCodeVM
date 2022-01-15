package intcode.instructions;

import intcode.AccessMode;
import intcode.Memory;
import intcode.Registers;

/**
 * This class implements the print instruction with op code 04.
 */
public class Print extends Instruction {
    public Print(Memory memory, Registers registers) {
        super(memory, registers);
    }

    @Override
    public void execute(AccessMode[] modes) {
        int value = readParameter(modes[0]);
        System.out.println(value);
    }
}
