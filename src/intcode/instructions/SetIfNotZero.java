package intcode.instructions;

import intcode.AccessMode;
import intcode.Memory;
import intcode.Registers;

/**
 * Implements the instruction for a conditional jump on parameter != 0.
 * This instruction has op code 05.
 */
public class SetIfNotZero extends Instruction {
    public SetIfNotZero(Memory memory, Registers registers) {
        super(memory, registers);
    }

    @Override
    public void execute(AccessMode[] modes) {
        if(readParameter(modes[0]) != 0)
            this.registers.writeInstructionPointer(readParameter(modes[1]));
        else
            this.registers.incrementInstructionPointer();
    }
}
