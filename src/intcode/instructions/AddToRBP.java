package intcode.instructions;

import intcode.AccessMode;
import intcode.Memory;
import intcode.Registers;

public class AddToRBP extends Instruction {
    public AddToRBP(Memory memory, Registers registers) {
        super(memory, registers);
    }

    @Override
    public void execute(AccessMode[] modes) {
        int rbp = this.registers.readRbp();
        int n = readParameter(modes[0]);
        this.registers.writeRbp(rbp + n);
    }
}
