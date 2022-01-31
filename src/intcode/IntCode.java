package intcode;

import intcode.exceptions.InvalidInstruction;
import intcode.exceptions.InvalidMode;
import intcode.exceptions.VMNotReady;

import java.util.Scanner;

public class IntCode {
    public static void main(String[] args) {
        VM vm = new VM();
        Scanner stdin = new Scanner(System.in);
        String program = "";
        while(stdin.hasNext())
            program += stdin.next();
        vm.loadProgram(program);
        try {
            vm.start();
        } catch (InvalidInstruction | InvalidMode | VMNotReady e) {
            e.printStackTrace();
            System.out.println("This program is not properly written!");
        }
        vm.printMemory();
        vm.printRegisters();
    }
}
