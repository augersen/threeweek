package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.*;

import javafx.stage.Stage;
import main.Model.Ball;
import main.Model.Brick;
import main.Model.Model;
import main.Model.Platform;
import main.Model.Sound;
import main.menus.DeathMenu;

public class View2D extends JPanel implements Runnable{

    private Stage primaryStage;
    private Stage gameStage;

    int FPS = Config.FPS;

    Controller keyH = new Controller();
    Thread gameThread;

    // Initiate Entities
    Platform platform = new Platform();
    Ball ball = new Ball();
    Model model;
    Sound sound = new Sound();
    int score = 0;

    /* Sets up the initial properties of the game panel */
    public View2D(int height, int length){

        this.setPreferredSize(new Dimension(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        //setup platform
        platform.setSizes(100,25);
        platform.setPosition(Config.SCREEN_WIDTH/2 - platform.getSizeX()/2,(int)(Config.SCREEN_HEIGHT * 0.75));
        platform.setColor(Color.orange);
        platform.setSpeed(6);

        //setup ball
        ball.setSizes(25);
        ball.setPosition(Config.SCREEN_WIDTH/2 - ball.getRadius()/2, (int)(Config.SCREEN_HEIGHT * 0.75)- platform.getSizeY());
        ball.setColor(Color.pink);

        //setup model
        model = new Model(height, length);

    }

    /* Starts game thread, making the game actually run */
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();

        /* Game window gets focus after the JavaFX to Swing transition */
        javax.swing.SwingUtilities.invokeLater(() -> {
            javax.swing.JFrame topFrame = (javax.swing.JFrame) javax.swing.SwingUtilities.getWindowAncestor(this);
            topFrame.setAlwaysOnTop(true); // Window starts in front
            topFrame.setAlwaysOnTop(false); // Turns off again right away

            topFrame.toFront();
            topFrame.requestFocus();
            this.requestFocusInWindow(); // Also gets keyboard focus
        });
    }

    @Override
    /* The game loop */
    public void run() {

        //Keeps track of time between frames. FPS is by default set to 60.
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        //Game thread is the thread running it all.
        while(gameThread != null){
            // update: update information such as character positions, score, etc.
            update();

            //Refactor to work with multiple balls
            if(!ball.isLive()){
                System.out.println("You dead as shit"); //Temporary
                //Frederik Tom Kronborg Paludan aka Palu aka Jeff aka Pookie aka mynamajeff på DTI
                //TODO AAAAAAAAAAAAAAAAAAH JEG KAN IKKE FÅ DEATHSCREEN TIL AT VISE PÅ EN SMOOTH MÅDE!!! kh jeff
                showDeathScreen();
                return;

            }

            // draw: draw the updated information
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();

                if(remainingTime < 0){
                    remainingTime = 0;
                }
                //Sleeps just long enough to ensure 60 FPS.
                Thread.sleep((long)remainingTime / 1000000);

                nextDrawTime = System.nanoTime() + drawInterval;

            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
    }

    //Method for calling every entities update, as well as collecting keyboard inputs.
    public void update() {
        //Checks if 'a' is pressed. Platform goes left if a is pressed, goes right even faster if shift is also pressed.
        //Starts ball if ball hasn't been started.
        if(keyH.aPressed){
            if(!ball.isStarted()){ball.setVectorX(-ball.getSpeed()); ball.setVectorY(-ball.getSpeed()); ball.start();}
            if(keyH.shiftPressed){
                platform.setX(platform.getX()- platform.getSpeed()*2);
            } else {
                platform.setX(platform.getX()- platform.getSpeed());
            }

        }

        //Checks if 'd' is pressed. Platform goes right if d is pressed, goes right even faster if shift is also pressed.
        //Starts ball if ball hasn't been started.
        if(keyH.dPressed){
            if(!ball.isStarted()){ball.setVectorX(ball.getSpeed()); ball.setVectorY(-ball.getSpeed()); ball.start();}
            if(keyH.shiftPressed){
                platform.setX(platform.getX()+ platform.getSpeed()*2);
            } else {
                platform.setX(platform.getX()+ platform.getSpeed());
            }
        }
        platform.collision(Config.SCREEN_WIDTH);

        //Refactor ball code to allow multiple balls later on!
        score += ball.update(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT, platform, model.bricks, sound);

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

    private void showDeathScreen() {
        javafx.application.Platform.runLater(() -> {
            if (gameStage != null) {
                gameStage.close(); // Close the game window
            }

            // Launch the DeathMenu
            try {
                Stage deathStage = new Stage();
                DeathMenu deathMenu = new DeathMenu(score);
                deathMenu.start(deathStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void setGameStage(Stage stage) {
        this.gameStage = stage;
    }

}