package intcode.instructions;

import intcode.AccessMode;
import intcode.Memory;
import intcode.Registers;

/**
 * La classe che rappresenta l'istruzione per l'incremento del relative base pointer, dato 1 parametro questo viene
 * sommato al relative base pointer.
 */
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
