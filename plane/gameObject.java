
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class gameObject extends JPanel implements ActionListener {

    private Timer time;
    Image sea;
    Image myPlane;
    private BufferedImage bimg;
    Graphics2D g2;
    int speed = 4, move = 0;
    Random generator = new Random(1234567);
    Island I1, I2, I3;
    playerPlane p1,p2;
    int w = 640, h = 480;
    public ArrayList<enemyPlane> enemies = new ArrayList<enemyPlane>();
    public ArrayList<bullet> enembul = new ArrayList<bullet>();
    public ArrayList<explosion> explosions = new ArrayList<explosion>();
    private powerup pup;
    boolean gameOver;
    ImageObserver observer;

    public gameObject() {
        
        setBackground(Color.white);
        Image island1, island2, island3, enemynoshoot, enemyshoot;

        try {
        //sea = getSprite("Resources/water.png");
        sea = ImageIO.read(new File("Resources/water.png"));
        island1 = ImageIO.read(new File("Resources/island1.png"));
        island2 = ImageIO.read(new File("Resources/island2.png"));
        island3 = ImageIO.read(new File("Resources/island3.png"));
        myPlane = ImageIO.read(new File("Resources/myplane_1.png"));
        enemynoshoot = ImageIO.read(new File("Resources/enemy1_1.png"));
        enemyshoot = ImageIO.read(new File("Resources/enemy2_1.png"));

        for(int i = 0; i<5; i++){
            int xpos = 50 + generator.nextInt(w - 100);
            int ypos = generator.nextInt((500)+h);
            enemyPlane tempenem = new enemyPlane(enemynoshoot, xpos, ypos, 1, false);
            tempenem.setmup(true);
            enemies.add(tempenem);
        }

        for(int i = 0; i<5; i++){
            int xpos = 50 + generator.nextInt(w - 100);
            int ypos = -generator.nextInt((500)+50);
            enemyPlane tempenem = new enemyPlane(enemyshoot, xpos, ypos, 1, true);
            tempenem.setmdown(true);
            enemies.add(tempenem);
        }
        
        gameOver = false;
        observer = this;

        I1 = new Island(island1, 100, 100, speed, generator);
        I2 = new Island(island2, 200, 400, speed, generator);
        I3 = new Island(island3, 300, 200, speed, generator);

        p1 = new playerPlane(myPlane, 150, 360, 2,45);
        p2 = new playerPlane(myPlane, 450, 360, 2,45);
        p2.setAlive(false);
        pup = new powerup(50 + generator.nextInt(w - 100),-generator.nextInt((500)+50));
        addKeyListener(new keyInput());
        setFocusable(true);
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("Resources/bmg.mid"));
        Clip clip = AudioSystem.getClip();
        clip.open(inputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);

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
        

            if(e.getKeyCode()==KeyEvent.VK_F2){
                if(!p2.isAlive()){
                    p2.setAlive(true);
                }
            }
            if(p1.isAlive()){
                if(e.getKeyCode()==KeyEvent.VK_W){
                    p1.setmup(true);
                }

                if(e.getKeyCode()==KeyEvent.VK_A){
                    p1.setmleft(true);
                }

                if(e.getKeyCode()==KeyEvent.VK_S){
                    p1.setmdown(true);
                }

                if(e.getKeyCode()==KeyEvent.VK_D){
                    p1.setmright(true);
                }

                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    p1.setShoot(true);
                }
            }

            if(p2.isAlive()){
                if(e.getKeyCode()==KeyEvent.VK_NUMPAD8){
                    p2.setmup(true);;
                }   

                if(e.getKeyCode()==KeyEvent.VK_NUMPAD4){
                    p2.setmleft(true);
                }

                if(e.getKeyCode()==KeyEvent.VK_NUMPAD5){
                    p2.setmdown(true);
                }

                if(e.getKeyCode()==KeyEvent.VK_NUMPAD6){
                    p2.setmright(true);
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

            if(e.getKeyCode()==KeyEvent.VK_F2){
                if(!p2.isAlive()){
                    p2.setAlive(true);
                }
            }

            if(p1.isAlive()){
                if(e.getKeyCode()==KeyEvent.VK_W){
                    p1.setmup(false);
                }

                if(e.getKeyCode()==KeyEvent.VK_A){
                    p1.setmleft(false);
                }

                if(e.getKeyCode()==KeyEvent.VK_S){
                    p1.setmdown(false);
                }

                if(e.getKeyCode()==KeyEvent.VK_D){
                    p1.setmright(false);
                }

                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    p1.setShoot(false);
                }
            }

            if(p2.isAlive()){
                if(e.getKeyCode()==KeyEvent.VK_NUMPAD8){
                    p2.setmup(false);;
                }   

                if(e.getKeyCode()==KeyEvent.VK_NUMPAD4){
                    p2.setmleft(false);
                }

                if(e.getKeyCode()==KeyEvent.VK_NUMPAD5){
                    p2.setmdown(false);
                }

                if(e.getKeyCode()==KeyEvent.VK_NUMPAD6){
                    p2.setmright(false);
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
        for(int i = 0; i< enemies.size(); i++){
            if(!enemies.get(i).canShoot()){
                enemies.get(i).setX(50 + generator.nextInt(w - 100));
                enemies.get(i).setY(generator.nextInt((500)+h));
            }
            if(enemies.get(i).canShoot()){
                enemies.get(i).setX(50 + generator.nextInt(w - 100));
                enemies.get(i).setY(-generator.nextInt((500)+50));
            }
        }
        
        p1 = new playerPlane(myPlane, 150, 360, 2,45);
        p2 = new playerPlane(myPlane, 450, 360, 2,45);
        p2.setAlive(false);
        enembul.clear();
        explosions.clear();
        gameOver = false;

    }

    public void actionPerformed(ActionEvent e){
        if(!gameOver){
        if(p1.isAlive()){

            if(p1.ismup() && p1.getY() >0){
                p1.moveUp();
            }

            if(p1.ismdown() && p1.getY() <400){
                p1.moveDown();
            }

            if(p1.ismleft() && p1.getX() >0){
                p1.moveLeft();
            }

            if(p1.ismright() && p1.getX() <565){
                p1.moveRight();
            }
            if(p1.getdel()>0){
                p1.setdel();
            }
            if(p1.getShoot() && p1.getdel() <=0){
                p1.shoot();
            }
        }

        if(p2.isAlive()){

            if(p2.ismup() && p2.getY() >00){
                p2.moveUp();
            }

            if(p2.ismdown() && p2.getY() <400){
                p2.moveDown();
            }

            if(p2.ismleft() && p2.getX() >0){
                p2.moveLeft();
            }

            if(p2.ismright() && p2.getX() <565){
                p2.moveRight();
            }
            if(p2.getdel()>0){
                p2.setdel();
            }
            if(p2.getShoot() && p2.getdel() <=0){
                p2.shoot();
            }
        }
        if(p1.isAlive() || p2.isAlive()) {
            detectCollisions();
            moveplayerbullets();
        }
        else{
            gameOver=true;
        }
        moveEnemy();
        enemyShoot();
        movepup();
        repaint();
    }
    }

    public void movepup(){
        pup.mdown();
        if(pup.getY() > 2*h){
            pup.setX(50 + generator.nextInt(w - 100));
            pup.setY(-100 - generator.nextInt(h - 100));
        }

    }

    public void detectCollisions(){
        ArrayList<bullet> p1bullet = p1.getbullets();
        ArrayList<bullet> p2bullet = p2.getbullets();
        
        for(int i =0;i<p1bullet.size();i++){ // check if player1 bullets hit enemies
            Rectangle temp = p1bullet.get(i).getBounds();
            for(int j=0;j<enemies.size();j++){
                if(p1bullet.get(i).isVisible()){
                if(enemies.get(j).getBounds().intersects(temp)){
                    p1.addkill();
                    playexplo1();
                    p1bullet.get(i).setVisible(false);
                    explosions.add(new explosion(enemies.get(j).getX(),enemies.get(j).getY()));
                    if(enemies.get(j).ismdown()){
                        enemies.get(j).setX(50 + generator.nextInt(w - 100));
                        enemies.get(j).setY(-100); //respawn enemies
                    }
                    else if(enemies.get(j).ismup()) {
                        enemies.get(j).setX(50 + generator.nextInt(w - 100));
                        enemies.get(j).setY(h+100);
                    }
                }
            }
            }
        }
        for(int i =0;i<p2bullet.size();i++){ // check if player2 bullets hit enemies
            Rectangle temp = p2bullet.get(i).getBounds();
            for(int j=0;j<enemies.size();j++){
                if(p2bullet.get(i).isVisible()){
                if(enemies.get(j).getBounds().intersects(temp)){
                    p2.addkill();
                    playexplo1();
                    p2bullet.get(i).setVisible(false);
                    explosions.add(new explosion(enemies.get(j).getX(),enemies.get(j).getY()));
                    if(enemies.get(j).ismdown()){
                        enemies.get(j).setX(50 + generator.nextInt(w - 100));
                        enemies.get(j).setY(-100); //respawn enemy
                    }
                    else if(enemies.get(j).ismup()) {
                        enemies.get(j).setX(50 + generator.nextInt(w - 100));
                        enemies.get(j).setY(h+100);
                    }
                }
            }
            }
        }
        for(int i=0; i<enemies.size();i++){ // check if enemies collide w/ players
            if(p1.getBounds().intersects(enemies.get(i).getBounds())&& p1.isAlive()){
                if(p1.getHP()<=1){
                    p1.setAlive(false);
                }
                else {
                    p1.loseHP();
                }
                p1.addkill();
                playexplo1();
                explosions.add(new explosion(enemies.get(i).getX(),enemies.get(i).getY()));
                if(enemies.get(i).ismdown()){
                        enemies.get(i).setX(50 + generator.nextInt(w - 100));
                        enemies.get(i).setY(-100); //respawn enemy
                    }
                else if(enemies.get(i).ismup()) {
                        enemies.get(i).setX(50 + generator.nextInt(w - 100));
                        enemies.get(i).setY(h+100);
                    }
            }
            if(p2.getBounds().intersects(enemies.get(i).getBounds())&& p2.isAlive()){
                if(p2.getHP()<=1){
                    p2.setAlive(false);
                }
                else {
                    p2.loseHP();
                }
                p2.addkill();
                playexplo1();
                explosions.add(new explosion(enemies.get(i).getX(),enemies.get(i).getY()));
                if(enemies.get(i).ismdown()){
                        enemies.get(i).setX(50 + generator.nextInt(w - 100));
                        enemies.get(i).setY(-100);  //respawn enemy
                    }
                else if(enemies.get(i).ismup()) {
                        enemies.get(i).setX(50 + generator.nextInt(w - 100));
                        enemies.get(i).setY(h+100);
                    }
            }
        }

        // check for collisions btwn players and enemy bullets
        for(int i =0;i<enembul.size();i++){
            if(p1.getBounds().intersects(enembul.get(i).getBounds()) && p1.isAlive()){
                if(p1.getHP()<=1){
                    p1.setAlive(false);
                    playexplo2();
                }
                else {
                    p1.loseHP();
                }
                enembul.get(i).setVisible(false);
            }
            if(p2.getBounds().intersects(enembul.get(i).getBounds()) && p2.isAlive()){
                if(p2.getHP()<=1){
                    p2.setAlive(false);
                    playexplo2();
                }
                else {
                    p2.loseHP();
                }
                enembul.get(i).setVisible(false);
            }
        }

        //detects if players pick up powerup;
        if(p1.getBounds().intersects(pup.getBounds())){
            p1.setmasterdel();
            pup.setX(50 + generator.nextInt(w - 100));
            pup.setY(-500 - generator.nextInt(h - 100));
        }
        if(p2.getBounds().intersects(pup.getBounds())){
            p2.setmasterdel();
            pup.setX(50 + generator.nextInt(w - 100));
            pup.setY(-500 - generator.nextInt(h - 100));            
        }
    }

    public void enemyShoot(){
        for(int i =0; i<enemies.size();i++){
            if(enemies.get(i).canShoot() && enemies.get(i).getdel() <=0){
                enembul.add(enemies.get(i).shoot());
            }
            else if(enemies.get(i).canShoot() && enemies.get(i).getdel() >0){
                enemies.get(i).setdel();
            }
        }

        for(int i = 0; i< enembul.size();i++){
            if(enembul.get(i).getY() < h && enembul.get(i).isVisible()) enembul.get(i).moveDown();
            else enembul.remove(i);
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


    public class Island {

        Image img;
        int x, y, speed;
        Random gen;

        Island(Image img, int x, int y, int speed, Random gen) {
            this.img = img;
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.gen = gen;
        }

        public void update() {
            y += speed;
            if (y >= h) {
                y = -100;
                x = Math.abs(gen.nextInt() % (w - 30));
            }
        }

        public Image getImg(){
            return img;
        }
        public int getX(){
            return this.x;
        }

        public int getY(){
            return this.y;
        }

         public void draw(Graphics g) {
            g.drawImage(img, x, y, null);
        } 
    }
    public void drawBackGroundWithTileImage(Graphics g) {
        int TileWidth = sea.getWidth(this);
        int TileHeight = sea.getHeight(this);

        int NumberX = (int) (w / TileWidth);
        int NumberY = (int) (h / TileHeight);

        for (int i = -1; i <= NumberY; i++) {
            for (int j = 0; j <= NumberX; j++) {
                g.drawImage(sea, j * TileWidth, 
                        i * TileHeight + (move % TileHeight), TileWidth, 
                        TileHeight, this);
            }
        }
        move += speed;
    } 

    public void drawDemo(Graphics g) {
        
            drawBackGroundWithTileImage(g);
            I1.update();
            I2.update();
            I3.update();
            
            I1.draw(g);
            I2.draw(g);
            I3.draw(g);
            if(p1.isAlive()){
                g.drawImage(p1.getImage(), p1.getX(), p1.getY(), null);
            }
            if(p2.isAlive()){
                g.drawImage(p2.getImage(), p2.getX(), p2.getY(), null);
            }
        
    }

    public void moveEnemy(){
        for(int i =0;i<enemies.size(); i++){
            enemyPlane enem = enemies.get(i);

            if(enem.isAlive() && enem.canShoot()){
                enem.moveDown();
                enem.setmdown(true);
            }

            if(enem.isAlive() && !enem.canShoot()){
                enem.moveUp();
            }

            if(!enem.isAlive() || (enem.ismdown() &&enem.getY()>h+10)) {
                enem.setX(50 + generator.nextInt(w - 100));
                enem.setY(-100);
            }

            if(!enem.isAlive() || (enem.ismup()&& enem.getY()<-30)) {
                enem.setX(50 + generator.nextInt(w - 100));
                enem.setY(h+100);
            }
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


    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D gfx = (Graphics2D)g;
        drawDemo(gfx);
        gfx.drawImage(bimg, 0, 0, this);
        drawEnemy(gfx);
        drawbullets(gfx);
        drawExplo(gfx);
        drawpup(gfx);
        if(p1.isAlive()){
            gfx.setFont(new Font("SansSerif", Font.BOLD, 10));
            gfx.setColor(Color.white);
            gfx.drawString("Player 1", 5,35);
            gfx.drawString("HP:  " + String.valueOf(p1.getHP()), 5,50);
            gfx.drawString("Kills:  " + String.valueOf(p1.getkills()), 5,62);
        }
        if(p2.isAlive()){
            gfx.setFont(new Font("SansSerif", Font.BOLD, 10));
            gfx.setColor(Color.white);
            gfx.drawString("Player 2", w-60,35);
            gfx.drawString("HP:  " + String.valueOf(p2.getHP()), w-60,50);
            gfx.drawString("Kills:  " + String.valueOf(p2.getkills()), w-60,62);
        }
        else if(!gameOver){
            gfx.setFont(new Font("SansSerif", Font.BOLD, 10));
            gfx.setColor(Color.white);
            gfx.drawString("Player 2", w-60,35);
            gfx.drawString("press F2", w-60,50);
            gfx.drawString("to enter", w-57,62);
        }

        if(!p1.isAlive() && !p2.isAlive()){
            gfx.setFont(new Font("SansSerif", Font.BOLD, 50));
            gfx.setColor(Color.white);
            gfx.drawString("GAME OVER", 170, h/2);
            gfx.setFont(new Font("SansSerif", Font.BOLD, 20));
            gfx.drawString("press ENTER to restart",220,h/2 +40);
            gfx.setFont(new Font("SansSerif", Font.BOLD, 10));
            gfx.drawString("Player 1 kills:  " + String.valueOf(p1.getkills()), 20,62);
            gfx.drawString("Player 2 kills:  " + String.valueOf(p2.getkills()), w-100,62);
        }
    }

    public void drawpup(Graphics g){
        g.drawImage(pup.getImage(),pup.getX(), pup.getY(), null);
    }

    public void drawEnemy(Graphics g){
        for(int i =0; i<enemies.size();i++){
            if(enemies.get(i).isAlive()){
                g.drawImage(enemies.get(i).getImage(), enemies.get(i).getX(), enemies.get(i).getY(),null);
            }
        }
    }

    public void drawbullets(Graphics g){
        if(p1.isAlive()){
            ArrayList<bullet> p1bullet = p1.getbullets();
            for(int i=0;i<p1bullet.size();i++){
                bullet temp = p1bullet.get(i);
                if(temp.isVisible()){
                g.drawImage(temp.getImg(), temp.getX(), temp.getY(),null);
                }
            }
        }

        if(p2.isAlive()){
            ArrayList<bullet> p2bullet = p2.getbullets();
            for(int i=0;i<p2bullet.size();i++){
                bullet temp = p2bullet.get(i);
                if(temp.isVisible()){
                g.drawImage(temp.getImg(), temp.getX(), temp.getY(),null);
                }
            }
        }

        for(int i = 0; i<enembul.size();i++){
            if(enembul.get(i).isVisible()){
            g.drawImage(enembul.get(i).getImg(),enembul.get(i).getX(), enembul.get(i).getY(), null);
            }
        }
    }

}