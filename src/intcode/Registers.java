package intcode;

public class Registers {
    private int instructionPointer;
    private int rbp;

    public Registers() {
        this.instructionPointer = 0;
        this.rbp = 0;
    }

    public void writeInstructionPointer(int value) {
        this.instructionPointer = value;
    }

    public void incrementInstructionPointer() {
        this.instructionPointer++;
    }

    public int readInstructionPointer() {
        return this.instructionPointer;
    }

    public void writeRbp(int value) {
        this.rbp = value;
    }

    public int readRbp() {
        return this.rbp;
    }
}
