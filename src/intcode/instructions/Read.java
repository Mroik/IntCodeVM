package intcode.instructions;

import intcode.AccessMode;
import intcode.Memory;
import intcode.Registers;

import java.util.Scanner;

public class Read extends Instruction {
    public Read(Memory memory, Registers registers) {
        super(memory, registers);
    }

    @Override
    public void execute(AccessMode[] modes) {
        Scanner stdin = new Scanner(System.in);
        int destinationAddress = getWriteAddress(modes[0]);
        this.memory.write(destinationAddress, stdin.nextInt());
    }
}
