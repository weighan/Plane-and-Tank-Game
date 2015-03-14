import javax.swing.*;
import java.awt.*;
import java.awt.Image;

public class powerup {

    int x, y, respt, mastert;
    boolean visible;
    Image img;
    public Image[] set = new Image[6];

    public powerup(int x, int y){
        this.x=x;
        this.y=y;
        ImageIcon i = new ImageIcon("Resources/powerup.png");
        img = i.getImage();     
        this.mastert = 2000;
        this.respt = mastert;
        this.visible = true;
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

    public void contdown(){
        if(!visible){
            if(this.respt>0){
                this.respt--;
            }
            else{
                this.visible = true;
                this.respt = mastert;
            }
        }
    }

    public void setVisible(boolean t){
        this.visible = t;
    }

    public boolean isVisible(){
        return visible;
    }

    public Image getImage(){
        return img;
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, img.getWidth(null), img.getHeight(null));
    }
}
