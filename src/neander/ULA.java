package neander;

public class ULA {

    private float a, b, s;
    private boolean Z;
    private boolean N;

    ULA() {}
    public void readA(float _a) { System.out.println("Reading ULA's entrace A"); a = _a; }
    public void readB(float _b) { System.out.println("Reading ULA's entrace B"); b = _b; }
    public float writeResult() {
        System.out.println("ULA's exit");
        return s;
    }

    public void operation(int code) {
        switch (code) {
            case 0:
                s = a + b;
                System.out.println("Summing "+a+" with "+b);
                break;
            case 1:
                s = a - b;
                System.out.println("Subtracting "+a+" with "+b);
                break;
            case 2:
                s = a * b;
                System.out.println("Multiplying "+a+" with "+b);
                break;
            case 3:
                s = a / b;
                System.out.println("Dividing "+a+" with "+b);
                break;
            case 4:
                s = (int)a & (int)b;
                System.out.println(a+" and "+b);
                break;
            case 5:
                s = (int)a | (int)b;
                System.out.println(a+" or "+b);
                break;
            case 6:
                s = -1 * a;
                System.out.println("Not "+a);
                break;
            default:
                break;
        }

        N = a < b;
        Z = a == b;
    }

    public boolean isZ() {
        return Z;
    }

    public void setZ(boolean z) {
        Z = z;
    }

    public boolean isN() {
        return N;
    }

    public void setN(boolean n) {
        N = n;
    }
}
