package intcode.instructions;

import intcode.AccessMode;
import intcode.Memory;
import intcode.Registers;

/**
 * La classe che rappresenta la istruzione di condizione se diverso da zero. Se il primo parametro è diverso da zero
 * allora viene scritto sull'instruction pointer il valore dato dal secondo parametro.
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
