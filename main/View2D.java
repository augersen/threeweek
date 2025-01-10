package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;
import main.Model.Ball;
import main.Model.Brick;
import main.Model.Model;
import main.Model.Player;

public class View2D extends JPanel implements Runnable{

    int FPS = 60;

    Controller keyH = new Controller();
    Thread gameThread;

    // Initiate Entities
    Player player = new Player();
    Ball ball = new Ball();
    Model model = new Model();
    int score = 0;

    /* Sets up the initial properties of the game panel */
    public View2D(){

        this.setPreferredSize(new Dimension(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        //setup player
        player.setSizes(100,25);
        player.setPosition(Config.SCREEN_WIDTH/2 - player.getSizeX()/2,(int)(Config.SCREEN_HEIGHT * 0.75));
        player.setColor(Color.orange);
        player.setSpeed(6);

        //setup ball
        ball.setSizes(25);
        ball.setPosition(Config.SCREEN_WIDTH/2 - ball.getRadius()/2, (int)(Config.SCREEN_HEIGHT * 0.75)-player.getSizeY());
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
                player.setX(player.getX()-player.getSpeed()*2);
            } else {
                player.setX(player.getX()-player.getSpeed());
            }
            
        }
        if(keyH.dPressed){
            if(keyH.shiftPressed){
                player.setX(player.getX()+player.getSpeed()*2);
            } else {
                player.setX(player.getX()+player.getSpeed());
            }
        }
        player.collision(Config.SCREEN_WIDTH);
        score += ball.update(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT, player, model.bricks);
    }

    //Standard method for drawing in JPanel
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(player.getColor());
        g2.fillRect(player.getX(),player.getY(),player.getSizeX(),player.getSizeY());

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
}
