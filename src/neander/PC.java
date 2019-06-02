package neander;

public class PC {

    private int EA = 0, PE;
    private boolean lastState;
    private Memory memory;
    private int cp;
    private int opULA;
    private int jc;
    private ULA ula;
    private REGS regs;
    private int A;

    PC (ULA ula, REGS regs, Memory memory) {
        this.ula = ula;
        this.regs = regs;
        this.memory = memory;
        this.lastState = false;
    }

    public void FTE () {
        switch (EA) {
            case 0:
                PE = 1;
                break;

            case 1:
                break;

            case 2:
                PE = 3;
                break;
            case 3:
                System.out.println(regs.getREM());
                if(regs.getRDM().toUpperCase().contains("STA")) PE = 5;
                else if (regs.getRDM().toUpperCase().contains("LDA")) PE = 7;
                else if (regs.getRDM().toUpperCase().contains("ADD")) { PE = 10; opULA = 0; }
                else if (regs.getRDM().toUpperCase().contains("OR")) { PE = 10; opULA = 5; }
                else if (regs.getRDM().toUpperCase().contains("AND")) { PE = 10; opULA = 4; }
                else if (regs.getRDM().toUpperCase().contains("JMP")) PE = 14;
                else if (regs.getRDM().toUpperCase().contains("JN")) { PE = 15; jc = 0; }
                else if (regs.getRDM().toUpperCase().contains("JZ")) { PE = 15; jc = 1; }
                break;

            case 4:
                lastState = true;
                break;
            case 5:
                PE = 6;
                break;
            case 6:
                PE = 0;
                cp++;
                break;
            case 7:
                PE = 8;
                break;
            case 8:
                PE = 9;
                break;
            case 9:
                PE = 0;
                break;
            case 10:
                PE = 11;
                break;
            case 11:
                PE = 12;
                break;
            case 12:
                PE = 0;
                break;
            case 13:
                PE = 0;
                break;
            case 14:
                PE = 0;
                cp++;
                break;
            case 15:
                PE = 0;
                cp++;
                break;
            default:
                break;
        }
    }

    public void FS() {
        switch (EA) {
            case 0:
                regs.LREM(memory.getCommand(cp));
                System.out.println(this.memory.getCommand(cp));
                break;
            case 1:
                System.out.println(regs.getREM());
                if (regs.getREM().toUpperCase().contains("NOT")) {
                    opULA = 6;
                    PE = 13;
                } else if (regs.getREM().toUpperCase().contains("HLT")) {
                    lastState = true;
                } else if (regs.getREM().toUpperCase().contains("NOP")) {
                    PE = 0;
                    cp++;
                } else {
                    PE = 2;
                }

                break;

            case 2:
                regs.LRDM(regs.getREM());
                regs.LREM(memory.getCommand(cp));
                cp++;
                PE = 3;
                break;

            case 3:
                regs.LRDM(regs.getREM());
                break;

            case 4:
                break;

            case 5:
                regs.LRDM(memory.getCommand(cp));
                regs.LREM(regs.getRDM());
                regs.LRDM(Integer.toString(A));
                break;

            case 6:
                memory.registerData(Integer.parseInt(regs.getREM()), Integer.parseInt(regs.getRDM()));
                break;

            case 7:
                regs.LRDM(memory.getCommand(cp));
                regs.LREM(Integer.toString(memory.getValue(Integer.parseInt(regs.getRDM()))));
                break;

            case 8:
                regs.LRDM(regs.getREM());
                break;

            case 9:
                A = Integer.parseInt(regs.getRDM());
                cp++;
                break;

            case 10:
                regs.LRDM(memory.getCommand(cp));
                regs.LREM(Integer.toString(memory.getValue(Integer.parseInt(regs.getRDM()))));
                break;

            case 11:
                regs.LRDM(regs.getREM());
                ula.readA(A);
                ula.readB(Integer.parseInt(regs.getRDM()));
                ula.operation(opULA);
                break;

            case 12:
                A = (int) ula.writeResult();
                cp++;
                break;

            case 13:
                ula.readA(A);
                ula.operation(opULA);
                A = (int) ula.writeResult();
                cp++;
                break;

            case 14:
                LCP(Integer.parseInt(regs.getRDM()));
                break;

            case 15:
                if (jc == 0) {
                    if (ula.isN()) {
                        LCP(Integer.parseInt(regs.getRDM()));
                    }
                } else {
                    if (ula.isZ()) {
                        LCP(Integer.parseInt(regs.getRDM()));
                    }
                }
                break;

            default:
                break;

        }
    }

    void LCP(int jump) {
        cp = jump;
    }

    public void updateState() {
        System.out.println("PE: " + PE);
        System.out.println("Acc: " + A);
        EA = PE;
    }

    public boolean isLastState() {
        return lastState;
    }
}
