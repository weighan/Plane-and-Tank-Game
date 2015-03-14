
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedReader;  
import java.io.FileReader;
import java.lang.Math;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;


public class gameObject extends JPanel implements ActionListener {

    private Timer time;
    Image player, player2, background;
    private BufferedImage bimg;
    Graphics2D g2;
    int speed = 4, move = 0;
    tank p1, p2;
    int w ,h;
    public ArrayList<wall> walls = new ArrayList<wall>();
    public ArrayList<bullet> p1bul = new ArrayList<bullet>();
    public ArrayList<bullet> p2bul = new ArrayList<bullet>();
    public ArrayList<explosion> explosions = new ArrayList<explosion>();
    private powerup pup;
    boolean gameOver;
    ImageObserver observer;
    BufferedReader br;
    String line;

    public gameObject(int x, int y) {
        
        setBackground(Color.white);
        Image gwall, bwall;

        try {
        background = ImageIO.read(new File("Resources/Background.png"));
        player = ImageIO.read(new File("Resources/tank_green.png"));
        player2 = ImageIO.read(new File("Resources/tank_red.png"));
        
        gameOver = false;
        observer = this;
        this.w = x;
        this.h = y;

        p1 = new tank(player, 150, 150, 4,20);
        p2 = new tank(player2, 1120-150, 1120-150, 4,20);
        
        addKeyListener(new keyInput());
        setFocusable(true);
        
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("Resources/bmg.mid"));
        Clip clip = AudioSystem.getClip();
        clip.open(inputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        
        int tempc = -1;
        br = new BufferedReader(new FileReader("Resources/map.txt"));
        while((line = br.readLine())!=null){
            tempc++;
            for(int i = 0; i<line.length(); i++){
                if(line.charAt(i)=='1'){
                    walls.add(new wall(i*32,tempc*32, false));
                }
                else if(line.charAt(i) =='2'){
                    walls.add(new wall(i*32,tempc*32, true));
                }
                else if(line.charAt(i) == '3'){
                    pup = new powerup(i*32, tempc*32);
                }
            }
        }

        time = new Timer(5, this);
        time.start();
        }
        catch (Exception e) {
            System.out.print("couldnt find resource file");
        }
    }

    public void playexplo1(){
        try{
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("Resources/snd_explosion1.wav"));
        Clip clip = AudioSystem.getClip();
        clip.open(inputStream);
        clip.loop(0);
        }
        catch (Exception e) {
            System.out.print("sound error");
        }
    }

    public void playexplo2(){
        try{
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("Resources/snd_explosion2.wav"));
        Clip clip = AudioSystem.getClip();
        clip.open(inputStream);
        clip.loop(0);
    }
        catch (Exception e) {
            System.out.print("sound error");
        }
    }
    private class keyInput extends KeyAdapter {

        public void keyPressed(KeyEvent e){
            if(e.getKeyCode()==KeyEvent.VK_ESCAPE){ // exit game
                System.exit(0);
            }
        
            if(p1.isAlive()){
                if(e.getKeyCode()==KeyEvent.VK_W){
                    p1.setmf(true);
                }

                if(e.getKeyCode()==KeyEvent.VK_A){
                    p1.tcw(true);
                }

                if(e.getKeyCode()==KeyEvent.VK_S){
                    p1.setmb(true);
                }

                if(e.getKeyCode()==KeyEvent.VK_D){
                    p1.tccw(true);
                }

                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    p1.setShoot(true);
                }
            }

            if(p2.isAlive()){
                if(e.getKeyCode()==KeyEvent.VK_NUMPAD8){
                    p2.setmf(true);
                }   

                if(e.getKeyCode()==KeyEvent.VK_NUMPAD4){
                    p2.tcw(true);
                }

                if(e.getKeyCode()==KeyEvent.VK_NUMPAD5){
                    p2.setmb(true);
                }

                if(e.getKeyCode()==KeyEvent.VK_NUMPAD6){
                    p2.tccw(true);
                }

                if(e.getKeyCode()==KeyEvent.VK_RIGHT){
                    p2.setShoot(true);
                }
            }
            if(gameOver){
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    reset();
                }
            }
        
        }

        public void keyReleased(KeyEvent e){
            if(e.getKeyCode()==KeyEvent.VK_ESCAPE){ // exit game
                System.exit(0);
            }        

            if(p1.isAlive()){
                if(e.getKeyCode()==KeyEvent.VK_W){
                    p1.setmf(false);
                }

                if(e.getKeyCode()==KeyEvent.VK_A){
                    p1.tcw(false);
                }

                if(e.getKeyCode()==KeyEvent.VK_S){
                    p1.setmb(false);
                }

                if(e.getKeyCode()==KeyEvent.VK_D){
                    p1.tccw(false);
                }

                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    p1.setShoot(false);
                }
            }

            if(p2.isAlive()){
                if(e.getKeyCode()==KeyEvent.VK_NUMPAD8){
                    p2.setmf(false);
                }   

                if(e.getKeyCode()==KeyEvent.VK_NUMPAD4){
                    p2.tcw(false);
                }

                if(e.getKeyCode()==KeyEvent.VK_NUMPAD5){
                    p2.setmb(false);
                }

                if(e.getKeyCode()==KeyEvent.VK_NUMPAD6){
                    p2.tccw(false);
                }

                if(e.getKeyCode()==KeyEvent.VK_RIGHT){
                    p2.setShoot(false);
                }
            }
            if(gameOver){
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    reset();
                }
            }
        }
    }

    public void reset(){
                
        p1 = new tank(player, 150, 150, 4,20);
        p2 = new tank(player2, 1120-150, 1120-150, 4,20);
        explosions.clear();
        gameOver = false;

    }

    public void actionPerformed(ActionEvent e){
        if(!gameOver){
        if(p1.isAlive()){

            if(p1.ismf()){
                p1.movef();
            }

            if(p1.ismb() ){
                p1.moveb();
            }

            if(p1.istccw()){
                p1.tccw();
            }

            if(p1.istcw() ){
                p1.tcw();
            }
            if(p1.getdel()>0){
                p1.setdel();
            }
            if(p1.getShoot() && p1.getdel() <=0){
                p1.shoot();
            }
        }

        if(p2.isAlive()){

            if(p2.ismf()){
                p2.movef();
            }

            if(p2.ismb()){
                p2.moveb();
            }

            if(p2.istccw()){
                p2.tccw();
            }

            if(p2.istcw()){
                p2.tcw();
            }
            if(p2.getdel()>0){
                p2.setdel();
            }
            if(p2.getShoot() && p2.getdel() <=0){
                p2.shoot();
            }
        }
        if(p1.isAlive() && p2.isAlive()) {
            detectCollisions();
            moveplayerbullets();
            respawnStuff();
        }
        else{
            gameOver=true;
        }
        repaint();
    }
    }

    public void respawnStuff(){
        for(int i =0; i<walls.size(); i++){
            walls.get(i).contdown();
        }
        pup.contdown();
    }

    public void detectCollisions(){
        ArrayList<bullet> p1bullet = p1.getbullets();
        ArrayList<bullet> p2bullet = p2.getbullets();
        
        for(int i =0;i<p1bullet.size();i++){ // check if player1 bullets hit p2
            Rectangle temp = p1bullet.get(i).getBounds();
            if(p1bullet.get(i).isVisible()){
            if(p2.getBounds().intersects(temp)){
                    p2.loseHP();
                    playexplo1();
                    p1bullet.get(i).setVisible(false);
                    explosions.add(new explosion((int)temp.getX(),(int)temp.getY()));
                    if(!p2.isAlive()){
                        playexplo2();
                    }
                 }
            }
        }
        for(int i =0;i<p2bullet.size();i++){ // check if player2 bullets hit p1
            Rectangle temp = p2bullet.get(i).getBounds();
            if(p2bullet.get(i).isVisible()){
            if(p1.getBounds().intersects(temp)){
                    p1.loseHP();
                    playexplo1();
                    p2bullet.get(i).setVisible(false);
                    explosions.add(new explosion((int)temp.getX(),(int)temp.getY()));
                    if(!p1.isAlive()){
                        playexplo2();
                    }
                 }
            }
        }
        for(int i=0; i<walls.size();i++){ // check if players hit walls
            wall temp = walls.get(i);
            if(p1.getBounds().intersects(temp.getBounds())&& p1.isAlive() && temp.isVisible()){
                p1.setX(p1.getPX());
                p1.setY(p1.getPY());
            }
            if(p2.getBounds().intersects(temp.getBounds())&& p2.isAlive() && temp.isVisible()){
                p2.setX(p2.getPX());
                p2.setY(p2.getPY());
            }
            //check if players' bullets hit walls
            for(int j= 0; j< p1bullet.size();j++){
                bullet t = p1bullet.get(j);
                if(temp.getBounds().intersects(t.getBounds()) && temp.isVisible() && t.isVisible()){
                    if(temp.getBreakable()){
                        temp.loseHP();
                        playexplo1();
                        explosions.add(new explosion((int)t.getX(),(int)t.getY()));
                    }
                    t.setVisible(false);
                }

            }
            for(int j= 0; j< p2bullet.size();j++){
                bullet t = p2bullet.get(j);
                if(temp.getBounds().intersects(t.getBounds()) && temp.isVisible() && t.isVisible()){
                    if(temp.getBreakable()){
                        temp.loseHP();
                        playexplo1();
                        explosions.add(new explosion((int)t.getX(),(int)t.getY()));
                    }
                    t.setVisible(false);
                }

            }
            
        }
        //check if players touch each other
        if(p1.getBounds().intersects(p2.getBounds())){
            p1.setX(p1.getPX());
            p1.setY(p1.getPY());
            p2.setX(p2.getPX());
            p2.setY(p2.getPY());
        }

        //detects if players pick up powerup;
        if(p1.getBounds().intersects(pup.getBounds()) && pup.isVisible()){
            p1.setmasterdel();
            pup.setVisible(false);
        }
        if(p2.getBounds().intersects(pup.getBounds()) && pup.isVisible()){
            p2.setmasterdel();
            pup.setVisible(false);
        }
    }
    
    public void moveplayerbullets(){
        ArrayList<bullet> p1bullet = p1.getbullets(); // p1 bullets
        for(int i=0; i<p1bullet.size();i++){
            bullet temp = p1bullet.get(i);
            if(temp.getY()>-20 ||temp.isVisible()) temp.moveUp();
            else p1bullet.remove(i);
        }
        ArrayList<bullet> p2bullet = p2.getbullets();  // p2 bullets
        for(int i=0; i<p2bullet.size();i++){
            bullet temp = p2bullet.get(i);
            if(temp.getY() >-20 ||temp.isVisible()) temp.moveUp();
            else p2bullet.remove(i);
        }

    }

    public void drawBackGroundWithTileImage(Graphics g) {
        int TileWidth = background.getWidth(this);
        int TileHeight = background.getHeight(this);

        int NumberX = (int) (1000 / TileWidth);
        int NumberY = (int) (1000 / TileHeight);

        for (int i = -1; i <= NumberY; i++) {
            for (int j = -1; j <= NumberX; j++) {
                g.drawImage(background, j * TileWidth, 
                        i * TileHeight + (move % TileHeight), TileWidth, TileHeight, this);
            }
        }
    } 

    public void drawWalls(Graphics g){
        for(int i = 0; i < walls.size(); i++){
            wall walle = walls.get(i);
            if(walle.isVisible()){
                g.drawImage(walle.getImage(), walle.getX(), walle.getY(), null);
            }
        }
    }

    public void drawPlayers(Graphics g) {
            if(p1.isAlive()){
                double angle = Math.toRadians(p1.getAngle());
                AffineTransform tsfm = AffineTransform.getRotateInstance(angle,p1.getImage().getWidth(null)/2,p1.getImage().getHeight(null)/2);
                AffineTransformOp op = new AffineTransformOp(tsfm, AffineTransformOp.TYPE_BILINEAR);
                g.drawImage(op.filter((BufferedImage)p1.getImage(),null), (int)p1.getX(),(int)p1.getY(), null);
            }
            if(p2.isAlive()){
                double angle = Math.toRadians(p2.getAngle());
                AffineTransform tsfm = AffineTransform.getRotateInstance(angle,p2.getImage().getWidth(null)/2,p2.getImage().getHeight(null)/2);
                AffineTransformOp op = new AffineTransformOp(tsfm, AffineTransformOp.TYPE_BILINEAR);
                g.drawImage(op.filter((BufferedImage)p2.getImage(),null), (int)p2.getX(),(int)p2.getY(), null);
            }
        
    }

    public void drawExplo(Graphics g){
        for(int i =0; i< explosions.size();i++){
            if(explosions.get(i).getexplonum()<6){
                g.drawImage(explosions.get(i).getImages()[explosions.get(i).getexplonum()], explosions.get(i).getX(), explosions.get(i).getY(), null);
                explosions.get(i).setexplonum();
            }
            else{
                explosions.remove(i);
            }
        }
    }

    public void drawSplitScreen(Graphics g, BufferedImage m ){
        //draw p1's side
        int tx = (int)p1.getX()-w/2;
        int ty = (int)p1.getY()-h/4;
        if(p1.getX()-w/2 < 0){
            tx = 0;
        }
        else if(p1.getX()+ w/2 > 1120){
            tx = 1120 - w;
        }
        if(p1.getY()-h/4 < 0){
            ty = 0;
        }
        else if(p1.getY()+h/4> 1120){
            ty = 1120 - h/2;
        }
        BufferedImage pl1 = m.getSubimage(tx, ty, w,h/2);
        //draw p2's side
        tx = (int)p2.getX()-w/2;
        ty = (int)p2.getY()-h/4;
        if(p2.getX()-w/2 < 0){
            tx = 0;
        }
        else if(p2.getX()+ w/2 > 1120){
            tx = 1120 - w;
        }
        if(p2.getY()-h/4 < 0){
            ty = 0;
        }
        else if(p2.getY()+h/4> 1120){
            ty = 1120 - h/2;
        }
        BufferedImage pl2 = m.getSubimage(tx, ty, w,h/2);
        g.drawImage(pl1, 0, 0, null);
        g.drawImage(pl2, 0, h/2, null);
        //draw minimap
        g.drawImage(m, w/2-64, h/2-64, 128, 128, null);
    }

    public void paint(Graphics g) {
        super.paint(g);
        BufferedImage m = new BufferedImage(1120,1120, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gfx = m.createGraphics();
        drawBackGroundWithTileImage(gfx);
        drawWalls(gfx);
        drawbullets(gfx);        
        drawpup(gfx);
        drawPlayers(gfx);
        drawExplo(gfx);
        drawSplitScreen(g, m);
        if(p1.isAlive()){
            g.setFont(new Font("SansSerif", Font.BOLD, 10));
            g.setColor(Color.white);
            g.drawString("Player 1", 5,35);
            g.drawString("HP:  " + String.valueOf(p1.getHP()), 5,50);
        }
        if(p2.isAlive()){
            g.setFont(new Font("SansSerif", Font.BOLD, 10));
            g.setColor(Color.white);
            g.drawString("Player 2", 5,h-70);
            g.drawString("HP:  " + String.valueOf(p2.getHP()), 5,h-55);
        }
        if(gameOver){
            g.setFont(new Font("SansSerif", Font.BOLD, 30));
            g.setColor(Color.white);
            g.drawString("Game Over", w/2-70,h/2);
            if(p1.isAlive()){
                g.drawString("Player 1 Wins", w/2-90,h/2+45);
            }
            else{
                g.drawString("Player 2 Wins", w/2-90,h/2+45);
            }
            g.drawString("Press ENTER to Restart", w/2-175,h/2+100);
        }

    }

    public void drawpup(Graphics g){
        if(pup.isVisible()){
            g.drawImage(pup.getImage(),pup.getX(), pup.getY(), null);
        }
    }

    public void drawbullets(Graphics g){
        if(p1.isAlive()){
            ArrayList<bullet> p1bullet = p1.getbullets();
            for(int i=0;i<p1bullet.size();i++){
                bullet temp = p1bullet.get(i);
                if(temp.isVisible()){
                g.drawImage(temp.getImg(), (int)temp.getX(), (int)temp.getY(),null);
                }
            }
        }

        if(p2.isAlive()){
            ArrayList<bullet> p2bullet = p2.getbullets();
            for(int i=0;i<p2bullet.size();i++){
                bullet temp = p2bullet.get(i);
                if(temp.isVisible()){
                g.drawImage(temp.getImg(), (int)temp.getX(), (int)temp.getY(),null);
                }
            }
        }

    }

}