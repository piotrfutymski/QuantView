import java.awt.*;
import javax.swing.JFrame;

public class VectorScreen extends Canvas
{
    StateVector vector;
    Potential potential;
    int width = 600;
    int height = 400;

    public void loadVectorAndPotential(StateVector v, Potential p)
    {
        vector = v;
        potential = p;
    }

    private int[] getPointSPositions()
    {
        int []res = new int[width];
        int i = 0;
        double [] prob = vector.getProbability();
        for (int p = 0; p < width; p++)
        {
            double v = 0;
            int l = 0;
            while(i * width < p * vector.getProbeNumber())
            {
                v += prob[i];
                i++;
                l++;
            }
            v/= l;
            v = v*height/40;
            res[p] = height - (int)v;
            if(res[p] < 0)
                res[p] = 0;
        }
        return res;
    }

    private int[] getPointPPositions()
    {
        int []res = new int[width];
        int i = 0;
        for (int p = 0; p < width; p++)
        {
            double v = 0;
            int l = 0;
            while(i * width < p * potential.data.length)
            {
                v += potential.data[i];
                i++;
                l++;
            }
            v/= l;
            v = v*height/4000000;
            res[p] = height - (int)v;
            if(res[p] < 0)
                res[p] = 0;
        }
        return res;
    }

    public void paint(Graphics g)
    {
        int[] pos = getPointSPositions();
        g.setColor(Color.BLUE);
        for (int i = 0; i < width; i++) {
            g.fillRect(i+20, pos[i]+20,1,1);
        }
        pos = getPointPPositions();
        g.setColor(Color.RED);
        for (int i = 0; i < width; i++) {
            g.fillRect(i+20, pos[i]+20,1,1);
        }
    }
}
