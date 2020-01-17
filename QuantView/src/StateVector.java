public class StateVector
{
    private int probeNumber = 10000;
    private Complex[] data;

    StateVector()
    {
        data = new Complex[probeNumber];
        data[probeNumber / 2].a = Math.sqrt(probeNumber);
    }

    StateVector(int n)
    {
        probeNumber = n;
        data = new Complex[probeNumber];
        data[probeNumber / 2].a = Math.sqrt(probeNumber);
    }

}
