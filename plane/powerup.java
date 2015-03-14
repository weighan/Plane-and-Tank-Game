import javax.swing.*;
import java.awt.*;
import java.awt.Image;

public class powerup {

    int x, y, speed;
    Image img;
    public Image[] set = new Image[6];

    public powerup(int x, int y){
        this.x=x;
        this.y=y;
        ImageIcon i = new ImageIcon("Resources/powerup.png");
        img = i.getImage();     
        this.speed = 1;   
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int i){
        x = i;
    }

    public void setY(int i){
        y =i;
    }

    public void mdown(){
        y+=speed;
    }

    public Image getImage(){
        return img;
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, img.getWidth(null), img.getHeight(null));
    }
}
