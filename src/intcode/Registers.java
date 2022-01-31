package intcode;

/**
 * Questa classe rappresenta i registri della VM.
 * Contiene 2 registri all'interno, l'instruction pointer e il relative base pointer.
 *
 * L'instruction pointer è il puntatore che fa riferimento all'istruzione da eseguire.
 * Il relative base pointer è un valore che serve per calcolare l'indirizzo di memoria a cui fare riferimento durante
 * lettura o scrittura di una istruzione.
 */
public class Registers {
    private int instructionPointer;
    private int rbp;

    /**
     * L'AF di this è l'insieme di registri della VM.
     *  - this.instructionPointer rappresenta l'instruction pointer.
     *  - this.rbp rappresenta il relative base pointer.
     *
     * IR:
     *  - this.instructionPointer >= 0
     */

    /**
     * Instanzia l'insieme dei registri con essi inizializzati a 0.
     */
    public Registers() {
        this.instructionPointer = 0;
        this.rbp = 0;
    }

    /**
     * PRE-CONDIZIONE:
     *  - value >= 0
     * POST-CONDIZIONE:
     *  - imposta il valore dell'instruction pointer a value.
     * EFFETTI-COLLATERALI:
     *  - modifica il valore dell'instruction pointer.
     */
    public void writeInstructionPointer(int value) {
        this.instructionPointer = value;
    }

    /**
     * POST-CONDIZIONE:
     *  - incrementa l'instruction pointer di 1.
     * EFFETTI-COLLATERALI:
     *  - modifica il valore dell'instruction pointer.
     */
    public void incrementInstructionPointer() {
        this.instructionPointer++;
    }

    /**
     * POST-CONDIZIONE:
     *  - restituisce il valore dell'instruction pointer.
     */
    public int readInstructionPointer() {
        return this.instructionPointer;
    }

    /**
     * POST-CONDIZIONE:
     *  - imposta il valore del relative base pointer a value.
     * EFFETTI-COLLATERALI:
     *  - modifica il valore del relative base pointer.
     */
    public void writeRbp(int value) {
        this.rbp = value;
    }

    /**
     * POST-CONDIZIONE:
     *  - restituisce il valore del relative base pointer.
     */
    public int readRbp() {
        return this.rbp;
    }
}
