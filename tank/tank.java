import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.io.File;
import javax.imageio.ImageIO;

public class tank {

	private Image tank;
    private double x, y, px, py, angle, angleinc;
	private int hp, speed, shotdel, masterdel;
	private boolean alive, mf, mb, tcw, tccw, shooting;
    private ArrayList<bullet> bullet;

	public tank(Image tank, double x, double y, int speed, int shotdel){
		this.x =x;
		this.y =y;
		this.hp = 10;
		this.tank = tank;
		this.speed = speed;
		this.alive =true;
		this.mf = false;
		this.mb = false;
		this.tcw = false;
		this.tccw = false;
        this.shooting = false;
        this.masterdel = shotdel;
        this.angle = 0;
        this.angleinc =1;
        bullet = new ArrayList<bullet>();
        this.px =x;
        this.py =x;
	}

    public void movef(){
        px = x;
        py = y;
    	x += Math.cos(Math.toRadians(angle)) * speed;
        y += Math.sin(Math.toRadians(angle)) * speed;
    }

    public void moveb(){
        px = x;
        py = y;
    	x -= Math.cos(Math.toRadians(angle)) * speed;
        y -= Math.sin(Math.toRadians(angle)) * speed;
    }

    public Image getImage(){
    	return tank;
    }

    public void setAngle(double a){
        this.angle = a;
    }

    public double getAngle(){
        return angle;
    }

    public void tccw(){
        if(angle >= 360){
            angle = 0;
        }
        else{
            this.angle += angleinc;
        }
    }

    public void tcw(){
        if(angle <= 0){
            angle = 360;
        }
        else{
            this.angle -= angleinc;
        }
    }

    public void setmf(boolean t){
    	this.mf = t;
    }
    public void setmb(boolean t){
    	this.mb = t;
    }
    public void tcw(boolean t){
    	this.tcw = t;
    }
    public void tccw(boolean t){
    	this.tccw = t;
    }

    public boolean ismf(){
    	return this.mf;
    }

    public boolean ismb(){
    	return this.mb;
    }

    public boolean istcw(){
    	return this.tcw;
    }

    public boolean istccw(){
    	return this.tccw;
    }

    public double getX(){
    	return this.x;
    }

    public double getPX(){
        return this.px;
    }

    public double getPY(){
        return this.py;
    }

    public double getY(){
    	return this.y;
    }

    public void setX(double i){
        x = i;
    }

    public void setY(double i){
        y = i;
    }


    public int getHP(){
    	return this.hp;
    }

    public void loseHP(){
        if(hp>0){
            hp--;
        }
        else{
            alive = false;
        }
    }

    public boolean isAlive(){
    	return this.alive;
    }

    public void setAlive(boolean m){
    	this.alive= m;    	
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
        bullet.add(new bullet(b, x+35, y+35,8, angle));
        shotdel = masterdel;
        }
        catch (Exception e) {
            System.out.print("couldnt find resource file");
        }

    }
    public void setmasterdel(){
        if(masterdel>0) masterdel -=5;
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
        return new Rectangle((int)x+20, (int)y+20, tank.getWidth(null)-30, tank.getHeight(null)-30);
    }
}
