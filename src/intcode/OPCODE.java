package intcode;

import intcode.instructions.*;

import java.lang.reflect.InvocationTargetException;

/**
 * Classe di costanti che fanno riferimento all'istruzione da instanziare.
 */
public enum OPCODE {
    ADD(Add.class),
    MULT(Mult.class),
    READ(Read.class),
    PRINT(Print.class),
    SET_IF_NOT_ZERO(SetIfNotZero.class),
    SET_IF_ZERO(SetIfZero.class),
    SET_TRUE_IF_LESS_THAN(SetTrueIfLessThan.class),
    SET_TRUE_IF_EQUALS(SetTrueIfEquals.class),
    ADD_TO_RBP(AddToRBP.class),
    EXIT(Exit.class);

    private Class<? extends Instruction> instruction;

    private <T extends Instruction> OPCODE(Class<T> instruction) {
        this.instruction = instruction;
    }

    public Instruction getInstruction(Memory memory, Registers registers) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return this.instruction.getConstructor(Memory.class, Registers.class).newInstance(memory, registers);
    }
}
