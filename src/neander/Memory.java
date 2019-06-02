package neander;

import java.util.ArrayList;
import java.util.List;

public class Memory {

    private List<String> commands = new ArrayList<>();
    private Integer[] data = new Integer[256];

    public Memory() {
        // Empty Constructor
    }

    public void register(List<String> reader, List<Integer> vars) {
        commands = reader;

        for(int x = 0; x < vars.size(); x += 2) {
            this.data[vars.get(x)] = vars.get(x+1);
            System.out.println(this.data[vars.get(x)]);
        }
        System.out.println(commands);
    }

    public void  registerData(int index, int value) { this.data[index] = value; }
    public String getCommand(int index) { return this.commands.get(index); }
    public int getValue(int index) { return this.data[index]; }
}
