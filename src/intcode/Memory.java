package intcode;

import java.util.ArrayList;

public class Memory {
    private ArrayList<Integer> cells;

    public Memory() {
        this.cells = new ArrayList<>();
    }

    public void write(int address, int value) {
        resizeRam(address);
        cells.set(address, value);
    }

    public int read(int address) {
        resizeRam(address);
        return cells.get(address);
    }

    private void resizeRam(int address) {
        for(int x = 0; x < address - (cells.size() - 1); x++)
            cells.add(0);
    }

    public int debugSize() {
        return this.cells.size();
    }
}
