package intcode;

import java.util.ArrayList;

/**
 * The abstraction of the memory of the VM.
 */
public class Memory {
    private ArrayList<Integer> cells;

    public Memory() {
        this.cells = new ArrayList<>();
    }

    /**
     * Writes a value to a memory cell.
     *
     * @param address The address to write to.
     * @param value The value to write into the address.
     */
    public void write(int address, int value) {
        resizeRam(address);
        cells.set(address, value);
    }

    public int read(int address) {
        resizeRam(address);
        return cells.get(address);
    }

    /**
     * Since the VM ideally has infinite memory we have to check the actual allocated size of the implementation,
     * this is to avoid out of bounds accesses.
     *
     * @param address The address that is required of the memory, if out of bounds new cells are allocated until the
     *                address is available.
     */
    private void resizeRam(int address) {
        for(int x = 0; x < address - (cells.size() - 1); x++)
            cells.add(0);
    }

    public int debugSize() {
        return this.cells.size();
    }
}
