package intcode.instructions;

import intcode.AccessMode;
import intcode.Memory;
import intcode.Registers;

public class SetIfZero extends Instruction {
    public SetIfZero(Memory memory, Registers registers) {
        super(memory, registers);
    }

    @Override
    public void execute(AccessMode[] modes) {
        if(readParameter(modes[0]) == 0)
            this.registers.writeInstructionPointer(readParameter(modes[1]));
        else
            this.registers.incrementInstructionPointer();
    }
}
