package intcode;

import intcode.exceptions.InvalidMode;

/**
 * Classe che rappresenta il tipo di accesso di una istruzione a memoria.
 * Ci sono 3 tipi di accesso:
 *  - POSITION: L'indirizzo di memoria è dato dal contenuto della cella di riferimento dell'indirizzo passato
 *  - IMMEDIATE: L'indirizzo di memoria è il valore stesso passato.
 *  - RELATIVE: Per avere l'indirizzo bisogna sommare il relative base pointer al valore passato.
 */
public enum AccessMode {
    POSITION,
    IMMEDIATE,
    RELATIVE;

    /**
     * POST-CONDIZIONI:
     *  - restituisce il tipo di accesso in base al codice di accesso.
     *  - se il codice di accesso non è valido lancia InvalidMode.
     */
    public static AccessMode fromCode(int code) throws InvalidMode {
        if(code == 0)
            return POSITION;
        else if(code == 1)
            return IMMEDIATE;
        else if(code == 2)
            return RELATIVE;
        else
            throw new InvalidMode();
    }
}
