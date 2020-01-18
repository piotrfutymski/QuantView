public class StateVector
{
    private int probeNumber = 600;
    private Complex[] data;


    public int getProbeNumber()
    {
        return probeNumber;
    }

    public StateVector()
    {
        data = new Complex[probeNumber];
        for (int i = 0; i < probeNumber; i++) {
            data[i] = new Complex();
        }
        data[probeNumber / 2].a = Math.sqrt(probeNumber);
    }

    public StateVector(int n)
    {
        probeNumber = n;
        data = new Complex[probeNumber];
        for (int i = 0; i < probeNumber; i++) {
            data[i] = new Complex();
        }
        data[probeNumber / 2].a = Math.sqrt(probeNumber);
    }

    public void countNextState(double deltaT, Potential potential)
    {
        Complex[] tDer = this.countStateTDerivative(potential);
        for (int i = 0; i < probeNumber; i++)
        {
            data[i] = Complex.add(data[i], Complex.multiply(tDer[i], new Complex(deltaT)));
        }
    }

    private Complex[] countStateTDerivative(Potential potential)
    {
        Complex[] res = new Complex[probeNumber];
        for (int i = 0; i < probeNumber; i++) {
            res[i] = new Complex();
        }
        for (int i = 0; i < probeNumber; i++)
        {
            if(i == 0)
                res[i] = Complex.add(Complex.multiply(new Complex(-2,0),data[0]),data[1]);
            else if(i == probeNumber - 1)
                res[i] = Complex.add(Complex.multiply(new Complex(-2,0),data[probeNumber - 1]),data[probeNumber - 2]);
            else
                res[i] = Complex.add(Complex.add(Complex.multiply(new Complex(-2,0),data[i]),data[i-1]), data[i+1]);
            res[i] = Complex.multiply(res[i], new Complex(((double)1/(double)probeNumber)));
            res[i] = Complex.multiply(res[i], new Complex(((double)1/(double)probeNumber)));
            res[i] = Complex.add(res[i], Complex.multiply(data[i],new Complex(potential.data[i])));
            res[i] = Complex.multiply(res[i], new Complex(0 ,-1));
        }
        return res;
    }

    public double[] getProbability()
    {
        double[] res = new double[probeNumber];
        for (int i = 0; i < probeNumber; i++)
        {
            res[i] = data[i].getNormSquared();
        }
        return res;
    }

}
