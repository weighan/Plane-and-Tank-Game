import javax.swing.*;
import java.awt.*;
import java.awt.Image;

public class wall {

    int x, y, hp, respt, mastert;
    Image img;
    boolean breakable, visible;

    public wall(int x, int y, boolean breakable){
        this.x=x;
        this.y=y;
        if(breakable){
            ImageIcon i = new ImageIcon("Resources/blue_wall.png");
            img = i.getImage();   
        }
        else{
            ImageIcon i = new ImageIcon("Resources/grey_wall.png");
            img = i.getImage();   
        }
        this.breakable = breakable;  
        this.hp = 3;
        this.mastert = 1000;
        this.respt =mastert;
        this.visible = true;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void contdown(){
        if(!visible){
            if(this.respt>0){
                this.respt--;
            }
            else{
                this.visible = true;
                hp = 5;
                this.respt = mastert;
            }
        }
    }

    public boolean getBreakable(){
        return breakable;
    }

    public boolean isVisible(){
        return visible;
    }

    public void setVisible(boolean x){
        this.visible =x;
    }

    public void loseHP(){
        if(hp >0){
            this.hp--;
        }
        else{
            visible = false;
        }
    }

    public void setX(int i){
        x = i;
    }

    public void setY(int i){
        y =i;
    }

    public Image getImage(){
        return img;
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, img.getWidth(null), img.getHeight(null));
    }
}
