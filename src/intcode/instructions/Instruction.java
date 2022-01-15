package intcode.instructions;

import intcode.AccessMode;
import intcode.Memory;
import intcode.Registers;

import java.util.Scanner;

public abstract class Instruction {
    protected Memory memory;
    protected Registers registers;

    public Instruction(Memory memory, Registers registers) {
        this.memory = memory;
        this.registers = registers;
    }

    public abstract void execute(AccessMode[] modes);

    protected int readParameter(AccessMode mode) {
        int parameter = this.memory.read(this.registers.readInstructionPointer());
        this.registers.incrementInstructionPointer();
        if(mode == AccessMode.DIRECT)
            return this.memory.read(parameter);
        else if(mode == AccessMode.VALUE)
            return parameter;
        else
            return this.memory.read(parameter + this.registers.readRbp());
    }

    protected int getWriteAddress(AccessMode mode) {
        int parameter = this.memory.read(this.registers.readInstructionPointer());
        this.registers.incrementInstructionPointer();
        if(mode == AccessMode.DIRECT)
            return parameter;
        else
            return parameter + this.registers.readRbp();
    }
}
