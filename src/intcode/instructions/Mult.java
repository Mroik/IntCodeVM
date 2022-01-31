package intcode.instructions;

import intcode.AccessMode;
import intcode.Memory;
import intcode.Registers;
import intcode.exceptions.InvalidMode;

/**
 * La classe che rappresenta la moltiplicazione, dati 2 parametri questi vengono moltiplicati tra loro. Il risultato
 * viene salvato all'indirizzo specificato dal terzo parametro.
 */
public class Mult extends Instruction {
    public Mult(Memory memory, Registers registers) {
        super(memory, registers);
    }

    @Override
    public void execute(AccessMode[] modes) throws InvalidMode {
        int n1, n2, destinationAddress;
        n1 = readParameter(modes[0]);
        n2 = readParameter(modes[1]);
        destinationAddress = getWriteAddress(modes[2]);
        this.memory.write(destinationAddress, n1 * n2);
    }
}
