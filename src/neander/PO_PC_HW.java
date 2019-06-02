package neander;

import java.io.IOException;
import java.util.Scanner;

public class PO_PC_HW {

    public static void main(String[] args) {
        int question;
        String path = "";
        Scanner scanner = new Scanner(System.in);

        System.out.print("Write the command: ");

        question = scanner.nextInt();

        switch (question) {
            case 1:
                path = "assets/summing.txt";
                break;
            case 2:
                path = "assets/subtracting.txt";
                break;
            case 3:
                path = "assets/q3.txt";
                break;
            default:
                break;
        }

        Memory memory = new Memory();
        try {
            memory.register(FileManager.read(path), FileManager.readData(path));
        } catch(IOException ioe) {
            ioe.printStackTrace();
            System.exit(1);
        }

        ULA ula = new ULA();
        REGS regs = new REGS();
        PC pc = new PC(ula, regs, memory);

        int count = 0;

        while(true) {
            count++;
            pc.FS();
            pc.FTE();
            pc.updateState();
            if(pc.isLastState()) break;
        }

        System.out.println("Cycles: " + count);
    }

}
