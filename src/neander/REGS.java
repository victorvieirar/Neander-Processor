package neander;

public class REGS {

    private String REM;
    private String RDM;

    public void LREM (String rem) { this.REM = rem; }
    public void LRDM (String rdm) { this.RDM = rdm; }
    public String getREM() { return this.REM; }
    public String getRDM() { return this.RDM; }

}
