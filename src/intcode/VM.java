package intcode;

import intcode.exceptions.InvalidInstruction;
import intcode.exceptions.InvalidMode;

import java.lang.reflect.InvocationTargetException;

/**
 * The abstraction of the VM.
 * An instruction cycle has been abstracted to 3 steps:
 * - Fetch: When the instruction if read from memory and the instructionPointer is then increased.
 * - Decode: Extracts op code and parameter modes from the instruction.
 * - Execute: When the instruction is finally executed.
 */
public class VM {
    private boolean running;
    private boolean ready;
    private Registers registers;
    private Memory memory;

    public VM() {
        this.running = false;
        this.ready = false;
        this.registers = new Registers();
        this.memory = new Memory();
    }

    /**
     * Loads the program into the memory of the VM.
     *
     * @param program A string representing a program.
     */
    public void loadProgram(String program) {
        String[] rawInstructions = program.split(",");
        for(int x = 0; x < rawInstructions.length ; x++)
            this.memory.write(x, Integer.valueOf(rawInstructions[x]));
        this.ready = true;
    }

    private int fetchInstruction() {
        int ip = this.registers.readInstructionPointer();
        int instruction = this.memory.read(ip);
        this.registers.incrementInstructionPointer();
        return instruction;
    }

    private int decode(int instruction, AccessMode[] decoded) throws InvalidInstruction {
        if(instruction < 1) {
            throw new InvalidInstruction();
        }
        int temp;
        int op = instruction % 100;
        instruction /= 100;
        for(int x = 0; x < 3; x++) {
            temp = instruction % 10;
            if(temp > 2)
                throw new InvalidInstruction();
            decoded[x] = AccessMode.fromCode(temp);
            instruction /= 10;
        }
        return op;
    }

    private void execute(int op, AccessMode[] modes) throws InvalidInstruction, InvalidMode {
        try {
            switch (op) {
                case 1:
                    OPCODE.ADD.getInstruction(this.memory, this.registers).execute(modes);
                    break;
                case 2:
                    OPCODE.MULT.getInstruction(this.memory, this.registers).execute(modes);
                    break;
                case 3:
                    OPCODE.READ.getInstruction(this.memory, this.registers).execute(modes);
                    break;
                case 4:
                    OPCODE.PRINT.getInstruction(this.memory, this.registers).execute(modes);
                    break;
                case 5:
                    OPCODE.SET_IF_NOT_ZERO.getInstruction(this.memory, this.registers).execute(modes);
                    break;
                case 6:
                    OPCODE.SET_IF_ZERO.getInstruction(this.memory, this.registers).execute(modes);
                    break;
                case 7:
                    OPCODE.SET_TRUE_IF_LESS_THAN.getInstruction(this.memory, this.registers).execute(modes);
                    break;
                case 8:
                    OPCODE.SET_TRUE_IF_EQUALS.getInstruction(this.memory, this.registers).execute(modes);
                    break;
                case 9:
                    OPCODE.ADD_TO_RBP.getInstruction(this.memory, this.registers).execute(modes);
                    break;
                case 99:
                    this.running = false;
                default:
                    throw new InvalidInstruction();
            }
        } catch (InvalidInstruction e) {
        } catch (IllegalAccessException e) {
        } catch (InstantiationException e) {
        } catch (NoSuchMethodException e) {
        } catch (InvocationTargetException e) {
        }
    }

    /**
     * Used to run the program, available only after having loaded the program into memory.
     *
     * @throws InvalidInstruction
     */
    public void start() throws InvalidInstruction, InvalidMode {
        if(!ready)
            System.out.println("Can't start VM, must load a program first!");
        AccessMode[] decoded = new AccessMode[3];
        this.ready = false;
        this.running = true;
        while(this.running) {
            int instruction = fetchInstruction();
            instruction = decode(instruction, decoded);
            execute(instruction, decoded);
        }
    }

    /**
     * Used for debugging
     */
    public void printMemory() {
        for(int x = 0; x < this.memory.debugSize(); x++)
            System.out.print(this.memory.read(x) + ", ");
        System.out.println();
    }

    public void printRegisters() {
        System.out.println("IP: " + this.registers.readInstructionPointer());
        System.out.println("RBP: " + this.registers.readRbp());
    }
}
