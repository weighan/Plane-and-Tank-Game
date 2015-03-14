import javax.swing.*;
import java.awt.*;
import java.awt.Image;

public class explosion {

    int x, y, explonum, internalcount;
    Image i1, i2, i3, i4, i5, i6;
    public Image[] set = new Image[6];

    public explosion(int x, int y){
        this.x=x;
        this.y=y;
        this.explonum = 0;
        this.internalcount = 0;
        ImageIcon img = new ImageIcon("Resources/explosion1_1.png");
        i1=img.getImage();
        img = new ImageIcon("Resources/explosion1_2.png");
        i2=img.getImage();
        img = new ImageIcon("Resources/explosion1_3.png");
        i3=img.getImage();
        img = new ImageIcon("Resources/explosion1_4.png");
        i4=img.getImage();
        img = new ImageIcon("Resources/explosion1_5.png");
        i5=img.getImage();
        img = new ImageIcon("Resources/explosion1_6.png");
        i6=img.getImage();
        set[0]=i1;
        set[1]=i2;
        set[2]=i3;
        set[3]=i4;
        set[4]=i5;
        set[5]=i6;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getexplonum(){
        return  explonum;
    }

    public void setexplonum(){
        if(internalcount<8){
            internalcount++;
        }
        else{
            internalcount=0;
            explonum++;
        }
    }

    public Image[] getImages(){
        return set;
    }
}
