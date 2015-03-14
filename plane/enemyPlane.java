import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;


public class enemyPlane implements plane {

	private Image plane;
	private int x, y, hp, speed, shotdelay;
	private boolean alive, canshoot, mup, mdown;
    private final int d = 250;

	public enemyPlane(Image plane, int x, int y, int speed, boolean shoot){
		this.x =x;
		this.y =y;
		this.hp = 1;
		this.plane = plane;
		this.speed = speed;
		this.alive =true;
		this.canshoot = shoot;
        this.mup = false;
        this.mdown = false;
        shotdelay = d;
	}

    public void moveRight(){
    	x +=speed;
    }
    public void moveLeft(){	
    	x -=speed;
    }
    public void moveUp(){
    	y-=speed;
    }
    public void moveDown(){
    	y+=speed;
    }

    public void moveDown(int s){
        y+=s;
    }

    public Image getImage(){
    	return plane;
    }

    public int getX(){
    	return this.x;
    }

    public void setX(int x){
        this.x =x;
    }

    public void setY(int y){
        this.y=y;
    }

    public int getY(){
    	return this.y;
    }

    public int getHP(){
    	return this.hp;
    }

    public boolean isAlive(){
    	return this.alive;
    }

    public void setAlive(boolean m){
    	this.alive= m;
    	
    }

    public void setShoot(boolean s){
        this.canshoot = s;
    }

    public boolean canShoot(){
        return canshoot;
    }

    public void setmup(boolean s){
        this.mup = s;
    }

    public void setmdown(boolean s){
        this.mdown= s;
    }

    public boolean ismup(){
        return mup;
    }

    public boolean ismdown(){
        return mdown;
    }

    public int getdel(){
        return shotdelay;
    }

    public void setdel(){
        shotdelay--;
    }

    public bullet shoot(){
        try{
        Image i = ImageIO.read(new File("Resources/enemybullet1.png"));
            shotdelay = d;
            return new bullet(i,x, y,2);
        }
        catch (Exception e) {
            System.out.print("couldnt find resource file");
        }
        
        return null;
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, plane.getWidth(null), plane.getHeight(null));
    }
}
