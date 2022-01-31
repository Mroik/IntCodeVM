package intcode;

import java.util.ArrayList;

/**
 * Questa classe rappresenta la memoria della VM. Idealmente questa memoria sarebbe infinità, in pratica è limitata
 * dalla quantità della macchina host.
 */
public class Memory {
    private ArrayList<Integer> cells;

    /**
     * AF:
     *  - this.cells indica le varie celle di memoria.
     *
     * IR:
     *  - this.cells != null
     */

    /**
     * POST-CONDIZIONI:
     *  - instanzia una nuova memoria di dimensione 0.
     */
    public Memory() {
        this.cells = new ArrayList<>();
    }

    /**
     * PRE-CONDIZIONI:
     *  - address >= 0
     * POST-CONDIZIONI:
     *  - scrive il valore di value nella cella di memoria a cui fa riferimento address.
     * EFFETTI-COLLATERALI:
     *  - potrebbe modificare la dimensione di this.cells.
     */
    public void write(int address, int value) {
        resizeRam(address);
        cells.set(address, value);
    }

    /**
     * PRE-CONDIZIONI:
     *  - address >= 0
     * POST-CONDIZIONI:
     *  - restituisce il valore nella cella a cui fa riferimento address.
     * EFFETTI-COLLATERALI:
     *  - potrebbe modificare la dimensione di this.cells.
     */
    public int read(int address) {
        resizeRam(address);
        return cells.get(address);
    }

    /**
     * PRE-CONDIZIONI:
     *  - address >= 0
     * POST-CONDIZIONI:
     *  - aggiunge celle di memoria se esse mancano per poter far riferimento ad address.
     * EFFETTI-COLLATERALI:
     *  - potrebbe modificare la dimensione di this.cells.
     */
    private void resizeRam(int address) {
        for(int x = 0; x < address - (cells.size() - 1); x++)
            cells.add(0);
    }

    /**
     * Metodo di debug non funzionale alla memoria o al suo utilizzo.
     * POST-CONDIZIONE:
     *  - restituisce la dimensione dell'ArrayList che rappresenta le celle di memoria.
     */
    public int debugSize() {
        return this.cells.size();
    }
}
