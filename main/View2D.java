package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import main.Model.Ball;
import main.Model.Brick;
import main.Model.Model;
import main.Model.Platform;
import main.Model.Sound;

public class View2D extends JPanel implements Runnable{

    // Gets resolution of screen
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

    final int screenWidth = (int) (size.getWidth() / 2.5 - 7); 
    final int screenHeight = (int) size.getHeight(); 

    int FPS = 60;

    Controller keyH = new Controller();
    Thread gameThread;

    // Initiate Entities
    Platform platform = new Platform();
    Ball ball = new Ball();
    Model model = new Model();
    Sound sound = new Sound();
    int score = 0;

    /* Sets up the initial properties of the game panel */
    public View2D(){

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        //setup platform
        platform.setSizes(100,25);
        platform.setPosition(screenWidth/2 - platform.getSizeX()/2,(int)(screenHeight * 0.75));
        platform.setColor(Color.orange);
        platform.setSpeed(6);

        //setup ball
        ball.setSizes(25);
        ball.setPosition(screenWidth/2 - ball.getRadius()/2, (int)(screenHeight * 0.75)- platform.getSizeY());
        ball.setColor(Color.pink);
        ball.setSpeed(8);

    }

    /* Starts game thread, making the game actually run */
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override 
    /* The game loop */
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        
        while(gameThread != null){
            // update: update information such as character positions
            update();

            // draw: draw the updated information
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                
                if(remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long)remainingTime / 1000000);

                nextDrawTime = System.nanoTime() + drawInterval;

            } catch (InterruptedException e) {
                
                e.printStackTrace();
            }
        }
    }

    public void update() {
        if(keyH.aPressed){
            if(keyH.shiftPressed){
                platform.setX(platform.getX()- platform.getSpeed()*2);
            } else {
                platform.setX(platform.getX()- platform.getSpeed());
            }
            
        }
        if(keyH.dPressed){
            if(keyH.shiftPressed){
                platform.setX(platform.getX()+ platform.getSpeed()*2);
            } else {
                platform.setX(platform.getX()+ platform.getSpeed());
            }
        }
        platform.collision(screenWidth);
        score += ball.update(screenWidth, screenHeight, platform, model.bricks, sound);
    }

    //Standard method for drawing in JPanel
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(platform.getColor());
        g2.fillRect(platform.getX(), platform.getY(), platform.getSizeX(), platform.getSizeY());

        //MAKE PRETTY - TEXT SHOWING SCORE ON BALL
        g2.setColor(Color.black);
        g2.drawString(this.score + "", platform.getX(),platform.getY()+platform.getSizeY()/2);

        g2.setColor(ball.getColor());
        g2.fillOval(ball.getX(),ball.getY(),ball.getRadius(),ball.getRadius());
        
        for(Brick[] brickList : model.bricks){
            for(Brick brick : brickList){
                g2.setColor(brick.getColor());
                g2.fillRect(brick.getX(),brick.getY(),brick.getSizeX(),brick.getSizeY());
            }
        }

        g2.dispose();
    }

    //method for getting score from game.
    public int getScore(){
        return this.score;
    }
}
