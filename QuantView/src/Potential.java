import java.io.Serializable;

public class Potential implements Serializable
{
    private int probeNumber = 600;
    public double[] data;

    public Potential()
    {
        data = new double[probeNumber];
    }

    public Potential(int n)
    {
        probeNumber = n;
        data = new double[probeNumber];
    }
}
