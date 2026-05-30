import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.Thread;



public class Main {

    public static void main(String[] args) {

        Frame frame = new Frame("superduper cool clock");

        frame.setLayout(new BorderLayout());
        MyCanvas myCanvas = new MyCanvas();
        frame.add("Center", myCanvas);
        frame.setSize(1000,800);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        frame.setVisible(true);

        Thread thread = new Thread(myCanvas);
        thread.start();
    }
}