package intcode.instructions;

import intcode.AccessMode;
import intcode.Memory;
import intcode.Registers;
import intcode.exceptions.InvalidMode;

import java.util.Scanner;

/**
 * Class that implements the instruction for op code 03.
 * It reads a value from STDIN and writes it to an address specified by the 1st parameter.
 */
public class Read extends Instruction {
    public Read(Memory memory, Registers registers) {
        super(memory, registers);
    }

    @Override
    public void execute(AccessMode[] modes) throws InvalidMode {
        Scanner stdin = new Scanner(System.in);
        int destinationAddress = getWriteAddress(modes[0]);
        this.memory.write(destinationAddress, stdin.nextInt());
    }
}
