package intcode.instructions;

import intcode.AccessMode;
import intcode.Memory;
import intcode.Registers;
import intcode.exceptions.InvalidMode;

/**
 * Classe che rappresenta un'istruzione di un programma IntCode.
 * L'oggetto istanziato è immutabile.
 */
public abstract class Instruction {
    /**
     * Contiene la memoria e i registri sul quale queste istruzioni dovranno fare le loro operazioni.
     * Dato che protected per mantenere l'immutabilità dell'oggetto bisogna fare attenzione a non modificare questi
     * valori nelle classi che estendono Instruction.
     */
    protected Memory memory;
    protected Registers registers;

    /**
     * AF:
     *  - this.memory è la memoria col quale l'istruzione dovrà interagire.
     *  - this.registers è l'inseme dei registri coi quali l'istruzione dovrà interagire.
     *
     * IR:
     *  - this.memory != null
     *  - this.registers != null
     */

    /**
     * POST-CONDIZIONI:
     *  - instanzia un oggetto Instruction con all'interno la memoria e i registri con cui l'istruzione dovrà interagire
     */
    public Instruction(Memory memory, Registers registers) {
        this.memory = memory;
        this.registers = registers;
    }

    /**
     * POST-CONDIZIONI:
     *  - esegue l'istruzione, modificando memoria e registri asseconda dell'istruzione.
     *  - se la modalità di accesso alla memoria non è valida per l'istruzione data allora lancia InvalidMode.
     * EFFETTI-COLLATERALI:
     *  - modifica memoria e/o registri.
     */
    public abstract void execute(AccessMode[] modes) throws InvalidMode;

    /**
     * POST-CONDIZIONI:
     *  - restituisce l'indirizzo effettivo da cui dovrà leggere un dato sulla base della modalità d'accesso.
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
     * POST-CONDIZIONI:
     *  - restituisce l'indirizzo effettivo sul quale l'istruzione dovrà scrivere sulla base della modalità d'accesso.
     *  - lancia InvalidMode se la modalità d'accesso non è valida in scrittura.
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
