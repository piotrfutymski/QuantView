import java.awt.*;
import javax.swing.JFrame;

public class VectorScreen extends Canvas
{
    StateVector vector;
    int width = 600;
    int height = 400;

    public void loadVector(StateVector v)
    {
        vector = v;
    }

    private int[] getPointPositions()
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
            v = v*height/10;
            res[p] = height - (int)v;
            if(res[p] < 0)
                res[p] = 0;
        }
        return res;
    }

    public void paint(Graphics g)
    {
        int[] pos = getPointPositions();
        g.setColor(Color.BLUE);
        for (int i = 0; i < width; i++) {
            g.fillRect(i+20, pos[i]+20,1,1);
        }
    }
}
