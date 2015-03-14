import javax.swing.*;
import java.awt.*;

public class bullet {

	private int speed;
    private double x, y, angle;
	private Image img;
	private boolean visible;

	public bullet(Image i,double x, double y, int speed, double a){
		this.x =x;
		this.y=y;
		this.speed=speed;
		img = i;
		this.visible=true;
        this.angle = a;
	}


    public double getY(){
    	return y;
    }
    public double getX(){
    	return x;
    }

    public int getSpeed(){
    	return speed;
    }
    public void moveUp(){
        x += Math.cos(Math.toRadians(angle)) * speed;
        y += Math.sin(Math.toRadians(angle)) * speed;
    }


    public void moveDown(){
    	y+=speed;
    	if(y>490) visible = false;
    }

    public double getAngle(){
        return angle;
    }

    public void setAngle(double a){
        angle = a;
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
    	return new Rectangle((int)x, (int)y, img.getWidth(null), img.getHeight(null));
    }
}
