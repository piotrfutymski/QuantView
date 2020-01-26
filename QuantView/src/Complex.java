import java.io.Serializable;

class Complex implements Serializable
{
    public double a;
    public double b;

    public Complex()
    {
        a = 0;
        b = 0;
    }

    public Complex(double f)
    {
        a = f;
        b = 0;
    }
    public Complex(double A, double B)
    {
        a = A;
        b = B;
    }

    public static Complex add(Complex A, Complex B)
    {
        Complex res = new Complex();
        res.a = A.a + B.a;
        res.b = A.b + B.b;
        return res;
    }

    public static Complex inverse(Complex A)
    {
        Complex res = new Complex();
        res.a = -A.a;
        res.b = -A.b;
        return res;
    }

    public static Complex conjugate(Complex A)
    {
        Complex res = new Complex();
        res.a = A.a;
        res.b = -A.b;
        return res;
    }

    public static Complex multiply(Complex A ,Complex B)
    {
        Complex res = new Complex();
        res.a = A.a*B.a-A.b*B.b;
        res.b = A.a*B.b + A.b*B.a;
        return res;
    }

    public static Complex expI(double alfa)
    {
        return new Complex(Math.cos(alfa), Math.sin(alfa));
    }

    public double getNormSquared()
    {
        return Complex.multiply(this,Complex.conjugate(this)).a;
    }
}