package intcode.instructions;

import intcode.AccessMode;
import intcode.Memory;
import intcode.Registers;

/**
 * This class is superfluous and is not actually functional to the interpreter.
 * It was added for completeness but op code 99 is a special case handled by the
 * execute function of the VM class.
 */
public class Exit extends Instruction {
    public Exit(Memory memory, Registers registers) {
        super(memory, registers);
    }

    @Override
    public void execute(AccessMode[] modes) {
    }
}
