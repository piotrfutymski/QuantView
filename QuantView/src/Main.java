import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

public class Main
{
    public static void main(String[] args) throws InterruptedException {
        Potential p = new Potential();
        StateVector s = new StateVector();
        for (int i = 0; i < 10000; i++) {
            s.countNextState(1000, p);
        }
        VectorScreen m = new VectorScreen();
        m.loadVector(s);
        JFrame f=new JFrame("Button Example");
        f.add(m);
        f.setSize(640,480);
        f.setVisible(true);
        while(true)
        {
            TimeUnit.MICROSECONDS.sleep(100);

        }
    }
}
