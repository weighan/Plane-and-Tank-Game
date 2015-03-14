import javax.swing.*;
import java.awt.*;

public class GameFrame {

    public GameFrame(){
        final gameObject demo = new gameObject();
        //demo.init();
        JFrame f = new JFrame("Scrolling Shooter");
        f.getContentPane().add("Center", demo);
        f.pack();
        f.setSize(new Dimension(640, 480));
        f.setVisible(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //demo.start();
    }

    public static void main(String[] args){
        new GameFrame();
    }
}