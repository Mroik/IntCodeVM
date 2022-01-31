package intcode.instructions;

import intcode.AccessMode;
import intcode.Memory;
import intcode.Registers;
import intcode.exceptions.InvalidMode;

/**
 * Rappresenta l'istruzione di addizione, dove dati 2 parametri il risultato viene scritto nell'indirizzo a cui fa
 * riferimento il terzo parametro.
 */
public class Add extends Instruction {
    public Add(Memory memory, Registers registers) {
        super(memory, registers);
    }

    @Override
    public void execute(AccessMode[] modes) throws InvalidMode {
        int n1, n2, destinationAddress;
        n1 = readParameter(modes[0]);
        n2 = readParameter(modes[1]);
        destinationAddress = getWriteAddress(modes[2]);
        this.memory.write(destinationAddress, n1 + n2);
    }
}
