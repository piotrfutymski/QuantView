import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import java.util.Locale;
import java.awt.event.*;

public class Main
{
    public static void loadFromFile(StateVector v,Potential p) throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream(new File("myObjects.txt"));
        ObjectInputStream oi = new ObjectInputStream(fi);
        v.data =((StateVector) oi.readObject()).data;
        p.data=((Potential) oi.readObject()).data;
        oi.close();
        fi.close();
    }
    public static void loadFromConsole(StateVector v, Potential p)
    {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);
        double sigma, p0;
        System.out.println("Give gaussian parameters");
        sigma = scanner.nextDouble();
        p0 = scanner.nextDouble();
        v.data =(StateVector.createGausseFunction(sigma, p0)).data;
        System.out.println("Give potential parameters");
        int start, lenght, value;
        start =scanner.nextInt();
        lenght =scanner.nextInt();
        value =scanner.nextInt();
        for (int i = start; i < start+lenght; i++) {
            p.data[i] = value;
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to load file? [0/1]");
        var y = scanner.nextInt();
        Potential p = new Potential();
        StateVector s = StateVector.createGausseFunction(0.01, -2);
        if(y == 1)
        {
            try {
                loadFromFile( s, p);
            }catch (FileNotFoundException | ClassNotFoundException e) {
                System.out.println("File not found, give parameters from console");
                loadFromConsole(s,p);
            }
        }
        else {
                loadFromConsole(s,p);
        }

        VectorScreen m = new VectorScreen();
        m.loadVectorAndPotential(s,p);
        m.setBounds(0,0,600,480);

        final boolean[] end = {false};
        JButton b=new JButton("End and Save to File");
        b.setBounds(100,490,300,30);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    FileOutputStream filestream = new FileOutputStream(new File("myObjects.txt"));
                    ObjectOutputStream o = new ObjectOutputStream(filestream);
                    o.writeObject(s);
                    o.writeObject(p);
                    o.close();
                    filestream.close();
                }catch (Exception e){
                    System.out.println("Problems");
                }
                end[0] = true;
            }
        });
        JFrame f=new JFrame("StateEvolution");
        f.add(m);
        f.add(b);
        f.setLayout(null);
        f.setSize(640,580);
        f.setVisible(true);
        while(!end[0])
        {
	        for (int i = 0; i < 10000; i++) {
                s.countNextState(0.000000001, p);
            }
            m.repaint();
        }
        System.exit(0);
    }
}
