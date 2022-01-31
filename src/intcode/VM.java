package intcode;

import intcode.exceptions.InvalidInstruction;
import intcode.exceptions.InvalidMode;
import intcode.exceptions.VMNotReady;

import java.lang.reflect.InvocationTargetException;

/**
 * Questa classe rappresenta la macchina ideale che interpreta le istruzioni in IntCode.
 * L'oggetto istanziato è un oggetto mutabile.
 * Tra i principali componenti abbiamo i registri e la memoria.
 *
 * I registri in questa macchina sono 2, l'instruction pointer e relative base pointer.
 * L'instruction pointer fa riferimento all'istruzione da eseguire mentre il relative base pointer viene utilizzato
 * per calcoli artimetici per trovare un indirizzo a cui bisognerà fare riferimento in una particolare istruzione.
 *
 * Per eseguire un programma sarà necessario prima caricarlo in memoria, per fare ciò usiamo il metodo loadProgram().
 * Per poi eseguirlo basterà chiamare il metodo start(). Questo metodo in particolare può essere chiamato solo se la
 * macchina è in stato di ready (che avviene dopo il caricamento di un programma) che può essere verificato con
 * isReady().
 */
public class VM {
    /**
     * running è variabile che indica se la macchina sta eseguendo un programma.
     * ready indica se la macchina è pronta ad eseguire un programma.
     */
    private boolean running;
    private boolean ready;
    private Registers registers;
    private Memory memory;

    /**
     * L'AF di this è una macchina che esegue codice IntCode.
     * Questa macchina ha vari stati tra cui running e ready.
     *  - this.running indica se la macchina sta eseguendo codice.
     *  - this.ready indica se la macchina è pronta a eseguire il codice in memoria.
     *  - this.registers rappresenta i registri della macchina.
     *  - this.memory rappresenta la memoria della macchina.
     *
     * IR:
     *  - this.registers != null
     *  - this.memory != null
     *  - this.ready == true se in memory c'è un programma
     */

    /**
     * POST-CONDIZIONI:
     *  - Istanzia la macchina con registri e memoria vuoti, e setta lo stato di ready a false.
     */
    public VM() {
        this.running = false;
        this.ready = false;
        this.registers = new Registers();
        this.memory = new Memory();
    }

    /**
     * POST-CONDIZIONI:
     *  - carica il programma descritto da program in memoria.
     *  - se program è una stringa null solleva NullPointerException.
     * EFFETTI COLLATERALI:
     *  - la memoria della macchina viene modificata
     *  - lo stato di ready viene messo a true
     */
    public void loadProgram(String program) {
        if(program == null)
            throw new NullPointerException();
        String[] rawInstructions = program.split(",");
        for(int x = 0; x < rawInstructions.length ; x++)
            this.memory.write(x, Integer.valueOf(rawInstructions[x]));
        this.ready = true;
    }

    /**
     * POST-CONDIZIONI:
     *  - restituisco l'istruzione a cui fa riferimento l'instruction pointer
     *  - aumento l'instruction pointer di 1
     * EFFETTI-COLLATERALI:
     *  - i registri vengono modificati
     */
    private int fetchInstruction() {
        int ip = this.registers.readInstructionPointer();
        int instruction = this.memory.read(ip);
        this.registers.incrementInstructionPointer();
        return instruction;
    }

    /**
     * POST-CONDIZIONI:
     *  - viene fatta la decodifica dell'istruzione e ne restituisco il codice operativo.
     *  - all'interno di decoded inserisco il tipo di accesso dell'istruzione.
     *  - se l'istruzione non è una istruzione valida allora viene lanciata InvalidInstruction.
     * EFFETTI-COLLATERALI:
     *  - decoded viene modificato.
     */
    private int decode(int instruction, AccessMode[] decoded) throws InvalidInstruction, InvalidMode {
        if(instruction < 1) {
            throw new InvalidInstruction();
        }
        int temp;
        int op = instruction % 100;
        instruction /= 100;
        for(int x = 0; x < 3; x++) {
            temp = instruction % 10;
            if(temp > 2)
                throw new InvalidMode();
            decoded[x] = AccessMode.fromCode(temp);
            instruction /= 10;
        }
        return op;
    }

    /**
     * PRE-CONDIZIONI:
     *  - op (il codice operativo) deve essere un codice valido (fare riferimento alla specifica di IntCode).
     * POST-CONDIZIONI:
     *  - esegue l'istruzione e modifica registri e memoria sulla base dell'istruzione.
     *  - se la modalità di accesso in riferimento all'istruzione non è valida allora viene lanciata InvalidMode.
     * EFFETTI-COLLATERALI:
     *  - memoria e/o registri vengono modificati.
     */
    private void execute(int op, AccessMode[] modes) throws InvalidMode {
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
                    break;
            }
        } catch (IllegalAccessException e) {
        } catch (InstantiationException e) {
        } catch (NoSuchMethodException e) {
        } catch (InvocationTargetException e) {
        }
    }

    /**
     * POST-CONDIZIONI:
     *  - restituisce lo stato di ready.
     */
    public boolean isReady() {
        return this.ready;
    }

    /**
     * POST-CONDIZIONI:
     *  - esegue il programma caricato in memoria.
     *  - lancia InvalidInstruction se nel programma una istruzione è malformata.
     *  - lancia InvalidMode se la modalità di accesso in riferimento al suo codice operativo di una istruzione non è
     *    valida.
     *  - lancia VMNotReady se si prova a utilizzare start() quando this.isReady() è false.
     * EFFETTI-COLLATERALI:
     *  - registri e/o la memoria vengono modificati.
     */
    public void start() throws InvalidInstruction, InvalidMode, VMNotReady {
        if(!ready)
            throw new VMNotReady();
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
     * Metodo di debug. Non è funzionale alla macchina e al suo utilizzo.
     * POST-CONDIZIONE:
     *  - stampa il contenuto della memoria.
     */
    public void printMemory() {
        for(int x = 0; x < this.memory.debugSize(); x++)
            System.out.print(this.memory.read(x) + ", ");
        System.out.println();
    }

    /**
     * Metodo di debug. Non è funzionale alla macchina e al suo utilizzo.
     * POST-CONDIZIONE:
     *  - stampa il contenuto dei registri.
     */
    public void printRegisters() {
        System.out.println("IP: " + this.registers.readInstructionPointer());
        System.out.println("RBP: " + this.registers.readRbp());
    }
}
