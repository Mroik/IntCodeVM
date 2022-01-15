package intcode.instructions;

import intcode.AccessMode;
import intcode.Memory;
import intcode.Registers;

public class SetTrueIfLessThan extends Instruction {
    public SetTrueIfLessThan(Memory memory, Registers registers) {
        super(memory, registers);
    }

    @Override
    public void execute(AccessMode[] modes) {
        int n1 = readParameter(modes[0]);
        int n2 = readParameter(modes[1]);
        int destinationAddress = getWriteAddress(AccessMode.VALUE);
        if(n1 < n2)
            this.memory.write(destinationAddress, 1);
        else
            this.memory.write(destinationAddress, 0);
    }
}
