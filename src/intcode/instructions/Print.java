package intcode.instructions;

import intcode.AccessMode;
import intcode.Memory;
import intcode.Registers;

/**
 * La classe che rappresenta l'istruzione di stampa a video. Stampa il valore dato dal primo parametro.
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
