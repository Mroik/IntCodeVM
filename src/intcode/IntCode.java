package intcode;

import intcode.exceptions.InvalidInstruction;
import intcode.exceptions.InvalidMode;

import java.util.Scanner;

public class IntCode {
    public static void main(String[] args) {
        VM vm = new VM();
        //Scanner stdin = new Scanner(System.in);
        //String program = "";
        //while(stdin.hasNext())
        //    program += stdin.next();
        //vm.loadProgram(program);
        vm.loadProgram("4,3,101,72,14,3,101,1,4,4,5,3,16,99,29,7,0,3,-67,-12,87,-8,3,-6,-8,-67,-23,-10");
        try {
            vm.start();
        } catch (InvalidInstruction | InvalidMode e) {
            e.printStackTrace();
            System.out.println("This program is not properly written!");
        }
        vm.printMemory();
        vm.printRegisters();
    }
}
