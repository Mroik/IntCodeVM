package intcode.instructions;

import intcode.AccessMode;
import intcode.Memory;
import intcode.Registers;
import intcode.exceptions.InvalidMode;

/**
 * L'istruzione che rappresenta la condizione di ugualianza. Se i primi 2 parametri sono uguali allora all'indirizzo di
 * memoria indicato dal terzo parametro viene scritto 1.
 */
public class SetTrueIfEquals extends Instruction {
    public SetTrueIfEquals(Memory memory, Registers registers) {
        super(memory, registers);
    }

    @Override
    public void execute(AccessMode[] modes) throws InvalidMode {
        int n1 = readParameter(modes[0]);
        int n2 = readParameter(modes[1]);
        int destinationAddress = getWriteAddress(modes[2]);
        if(n1 == n2)
            this.memory.write(destinationAddress, 1);
        else
            this.memory.write(destinationAddress, 0);
    }
}
