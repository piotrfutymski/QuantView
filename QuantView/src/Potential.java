
public class Potential
{
    private int probeNumber = 600;
    public double[] data;

    public Potential()
    {
        data = new double[probeNumber];
        for (int i = 350; i < 360; i++) {
            data[i] = 100000;
        }
    }

    public Potential(int n)
    {
        probeNumber = n;
        data = new double[probeNumber];
    }
}
