import java.io.Serializable;

public class StateVector implements Serializable {
    private int probeNumber = 600;
    public Complex[] data;
    private static double h= 1;


    public int getProbeNumber()
    {
        return probeNumber;
    }

    public static StateVector createGausseFunction(double sigma, double p0)
    {
        StateVector res = new StateVector();
        double normalizator = Math.pow(3.1412*sigma*sigma, 0.25);
        for (int i = 0; i < res.probeNumber; i++)
        {
            double x = (double)i/(double)res.probeNumber - 0.5;
            Complex y = Complex.expI(x*p0/h);
            double a = Math.exp(-x*x/(2*sigma*sigma))/normalizator;
            res.data[i] = Complex.multiply(y, new Complex(a));
        }
        return res;
    }

    private StateVector()
    {
        data = new Complex[probeNumber];
        for (int i = 0; i < probeNumber; i++) {
            data[i] = new Complex();
        }
    }

    private StateVector(int n)
    {
        probeNumber = n;
        data = new Complex[probeNumber];
        for (int i = 0; i < probeNumber; i++) {
            data[i] = new Complex();
        }
    }

    public void countNextState(double deltaT, Potential potential)
    {
        Complex[] tDer = this.countStateTDerivative(potential);
        for (int i = 0; i < probeNumber; i++)
        {
            data[i] = Complex.add(data[i], Complex.multiply(tDer[i], new Complex(deltaT)));
        }
    }

    private void normalize()
    {
        double sum = 0;
        var prob = this.getProbability();
        for (int i = 0; i < probeNumber; i++) {
            sum += prob[i];
        }
        sum/=probeNumber;
        sum =Math.sqrt(sum);
        for (int i = 0; i < probeNumber; i++)
        {
            data[i].a/=sum;
            data[i].b/=sum;
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
            if(i == 0 || i == probeNumber - 1)
                res[i] = new Complex(0);
            else {
                res[i] = Complex.add(Complex.add(Complex.multiply(new Complex(-2, 0), data[i]), data[i - 1]), data[i + 1]);
                res[i] = Complex.multiply(res[i], new Complex(probeNumber * probeNumber*h*h));
                res[i] = Complex.add(res[i], Complex.multiply(data[i], new Complex(potential.data[i])));
                res[i] = Complex.multiply(res[i], new Complex(0, -1));
                res[i] = Complex.multiply(res[i], new Complex(1/h));
            }
        }
        res[0] = res[1];
        res[probeNumber-1] = res[probeNumber-2];
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
