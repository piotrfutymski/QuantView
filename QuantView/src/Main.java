import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

public class Main
{
    public static void main(String[] args) throws InterruptedException {
        Potential p = new Potential();
        StateVector s = new StateVector();
        VectorScreen m = new VectorScreen();
        m.loadVector(s);
        JFrame f=new JFrame("Button Example");
        f.add(m);
        f.setSize(640,480);
        f.setVisible(true);
        while(true)
        {
	        for (int i = 0; i < 4000; i++)
	        {
                s.countNextState(0.00000001, p);
        	}
            m.repaint();
            TimeUnit.MICROSECONDS.sleep(300);
        }
    }
}
