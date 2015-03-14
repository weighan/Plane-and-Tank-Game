import javax.swing.*;
import java.awt.*;

public class bullet {

	private int x,y, speed;
	private Image img;
	private boolean visible;

	public bullet(Image i,int x, int y, int speed){
		this.x =x;
		this.y=y;
		this.speed=speed;
		img = i;
		this.visible=true;
	}


    public int getY(){
    	return y;
    }
    public int getX(){
    	return x;
    }

    public int getSpeed(){
    	return speed;
    }
    public void moveUp(){
    	y-=speed;
    	if(y<0) visible = false;
    }

    public void moveDown(){
    	y+=speed;
    	if(y>490) visible = false;
    }

    public void setVisible(boolean x){
    	visible = x;
    }
    public boolean isVisible(){
    	return visible;
    }

    public Image getImg(){
    	return img;
    }

    public Rectangle getBounds(){
    	return new Rectangle(x, y, img.getWidth(null), img.getHeight(null));
    }
}
