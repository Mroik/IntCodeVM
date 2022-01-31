package intcode.instructions;

import intcode.AccessMode;
import intcode.Memory;
import intcode.Registers;

/**
 * La classe che rappresenta l'istruzione di condizione se uguale a zero. Se il primo parametro è uguale a zero allora
 * scrive nell'instruction pointer il valore dato dal secondo parametro.
 */
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
