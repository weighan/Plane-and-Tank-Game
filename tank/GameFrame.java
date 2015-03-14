import javax.swing.*;
import java.awt.*;

public class GameFrame {

    public GameFrame(){
        int x = 640;
        int y = 480;
        final gameObject demo = new gameObject(x,y);
        //demo.init();
        JFrame f = new JFrame("Tank Game");
        f.getContentPane().add("Center", demo);
        f.pack();
        f.setSize(new Dimension(x, y));
        f.setVisible(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //demo.start();
    }

    public static void main(String[] args){
        new GameFrame();
    }
}