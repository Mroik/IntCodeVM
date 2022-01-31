package intcode.instructions;

import intcode.AccessMode;
import intcode.Memory;
import intcode.Registers;
import intcode.exceptions.InvalidMode;

import java.util.Scanner;

/**
 * La classe che rappresenta la lettura da standard input. Questo valore viene salvato nel primo parametro che fa
 * riferimento all'indirizzo di memoria della cella di destinazione.
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
