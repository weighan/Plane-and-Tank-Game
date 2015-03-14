import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.io.File;
import javax.imageio.ImageIO;

public class playerPlane implements plane {

	private Image plane;
	private int x, y, hp, speed, kills, shotdel, masterdel;
	private boolean alive, mup, mdown, mleft, mright, shooting;
    private ArrayList<bullet> bullet;

	public playerPlane(Image plane, int x, int y, int speed, int shotdel){
		this.x =x;
		this.y =y;
		this.hp = 10;
		this.plane = plane;
		this.speed = speed;
		this.kills =0;
		this.alive =true;
		this.mup = false;
		this.mdown = false;
		this.mleft = false;
		this.mright = false;
        this.shooting = false;
        this.masterdel = shotdel;
        bullet = new ArrayList<bullet>();
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

    public Image getImage(){
    	return plane;
    }

    public void setmup(boolean t){
    	this.mup = t;
    }
    public void setmdown(boolean t){
    	this.mdown = t;
    }
    public void setmleft(boolean t){
    	this.mleft = t;
    }
    public void setmright(boolean t){
    	this.mright = t;
    }

    public boolean ismup(){
    	return this.mup;
    }

    public boolean ismdown(){
    	return this.mdown;
    }

    public boolean ismleft(){
    	return this.mleft;
    }

    public boolean ismright(){
    	return this.mright;
    }

    public int getX(){
    	return this.x;
    }

    public int getY(){
    	return this.y;
    }

    public int getHP(){
    	return this.hp;
    }

    public void loseHP(){
        hp--;
    }

    public boolean isAlive(){
    	return this.alive;
    }

    public void setAlive(boolean m){
    	this.alive= m;    	
    }

    public void addkill(){
    	this.kills++;
    }

    public int getkills(){
        return kills;
    }

    public void setShoot(boolean s){
        this.shooting =s;
    }

    public boolean getShoot(){
        return shooting;
    }
    public void shoot(){
        try{
        Image b = ImageIO.read(new File("Resources/bullet.png"));
        bullet.add(new bullet(b,x+17,y,3));
        shotdel = masterdel;
        }
        catch (Exception e) {
            System.out.print("couldnt find resource file");
        }

    }
    public void setmasterdel(){
        if(masterdel>0) masterdel--;
        else hp++;
    }

    public void setdel(){
        shotdel--;
    }

    public int getdel(){
        return shotdel;
    }

    public ArrayList<bullet> getbullets(){
        return bullet;
    }

    public Rectangle getBounds(){
        return new Rectangle(x+20, y+20, plane.getWidth(null)-30, plane.getHeight(null)-30);
    }
}
