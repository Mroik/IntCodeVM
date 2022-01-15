package intcode.instructions;

import intcode.AccessMode;
import intcode.Memory;
import intcode.Registers;
import intcode.exceptions.InvalidMode;

/**
 * Base class for all instructions of IntCode.
 * Both memory and registers have to be passed to the constructor
 * to be able to interact with them.
 */
public abstract class Instruction {
    protected Memory memory;
    protected Registers registers;

    public Instruction(Memory memory, Registers registers) {
        this.memory = memory;
        this.registers = registers;
    }

    /**
     * The actual behaviour of the instruction is defined in execute.
     *
     * @param modes The modes of each parameter.
     */
    public abstract void execute(AccessMode[] modes) throws InvalidMode;

    /**
     * A helper function to get the proper value given as a parameter based on the AccessMode.
     *
     * @param mode The AccessMode of the parameter.
     * @return The value we should be working with.
     */
    protected int readParameter(AccessMode mode) {
        int parameter = this.memory.read(this.registers.readInstructionPointer());
        this.registers.incrementInstructionPointer();
        if(mode == AccessMode.POSITION)
            return this.memory.read(parameter);
        else if(mode == AccessMode.IMMEDIATE)
            return parameter;
        else
            return this.memory.read(parameter + this.registers.readRbp());
    }

    /**
     * Similar to readParameter() but instead returns the address we should be writing to based on the AccessMode.
     * Used for functions that write to memory.
     *
     * @param mode The AccessMode of the parameter
     * @return The address to write to.
     */
    protected int getWriteAddress(AccessMode mode) throws InvalidMode {
        int parameter = this.memory.read(this.registers.readInstructionPointer());
        this.registers.incrementInstructionPointer();
        if(mode == AccessMode.POSITION)
            return parameter;
        else if(mode == AccessMode.RELATIVE)
            return parameter + this.registers.readRbp();
        throw new InvalidMode();
    }
}
