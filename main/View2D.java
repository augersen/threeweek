package main;

import java.util.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.*;
import javafx.stage.Stage;
import main.Model.*;
import main.menus.BattlepassMenu;
import main.menus.DeathMenu;

public class View2D extends JPanel implements Runnable{

    private Stage gameStage;
    private java.awt.Image ballImage; 
    private java.awt.Image backgroundImage; 
    private java.awt.Image flameIcon = new ImageIcon("main/resources/images/flame-icon.png").getImage();
    private java.awt.Image bombIcon = new ImageIcon("main/resources/images/bomb-icon.png").getImage();
    private java.awt.Image plusIcon = new ImageIcon("main/resources/images/plus-icon.png").getImage();

    int FPS = Config.FPS;


    Controller keyH = new Controller();
    Thread gameThread;

    // Initiate Entities
    Platform platform = new Platform();
    AugustArray objects = new AugustArray();


    Model model;
    Sound sound = new Sound();
    int score = 0;
    int brickCount;

    /* Sets up the initial properties of the game panel */
    public View2D(int height, int length){

        this.setPreferredSize(new Dimension(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        // Battlepass modifiers
        switch (BattlepassMenu.battlepassConfig) {
            case "Battlepass1":
                ballImage = new ImageIcon("main/resources/images/btc-logo.png").getImage();
                backgroundImage = new ImageIcon("main/resources/images/Elon.png").getImage();
                break;
            case "Battlepass2":
                ballImage = new ImageIcon("main/resources/images/blue-ball.png").getImage();
                backgroundImage = new ImageIcon("main/resources/images/tiger-bg.png").getImage();
                break;
/*          case "Battlepass3":
                ballImage = new ImageIcon("main/resources/images/ltc-logo.png").getImage();
                backgroundImage = new ImageIcon("main/resources/images/Elon.png").getImage();
                break;
            case "Battlepass4":
                ballImage = new ImageIcon("main/resources/images/btc-logo.png").getImage();
                backgroundImage = new ImageIcon("main/resources/images/Elon.png").getImage();
                break;
            case "Battlepass5":
                ballImage = new ImageIcon("main/resources/images/blue-ball.png").getImage();
                backgroundImage = new ImageIcon("main/resources/images/tiger-bg.png").getImage();
                break;
            case "Battlepass6":
                ballImage = new ImageIcon("main/resources/images/ltc-logo.png").getImage();
                backgroundImage = new ImageIcon("main/resources/images/Elon.png").getImage();
                break;
            case "Battlepass7":
                ballImage = new ImageIcon("main/resources/images/btc-logo.png").getImage();
                backgroundImage = new ImageIcon("main/resources/images/Elon.png").getImage();
                break;
            case "Battlepass8":
                ballImage = new ImageIcon("main/resources/images/blue-ball.png").getImage();
                backgroundImage = new ImageIcon("main/resources/images/tiger-bg.png").getImage();
                break;
            case "Battlepass9":
                ballImage = new ImageIcon("main/resources/images/ltc-logo.png").getImage();
                backgroundImage = new ImageIcon("main/resources/images/Elon.png").getImage();
                break;
            case "Battlepass10":
                ballImage = new ImageIcon("main/resources/images/btc-logo.png").getImage();
                backgroundImage = new ImageIcon("main/resources/images/Elon.png").getImage();
                break;
            case "Battlepass11":
                ballImage = new ImageIcon("main/resources/images/blue-ball.png").getImage();
                backgroundImage = new ImageIcon("main/resources/images/tiger-bg.png").getImage();
                break;
            case "Battlepass12":
                ballImage = new ImageIcon("main/resources/images/ltc-logo.png").getImage();
                backgroundImage = new ImageIcon("main/resources/images/Elon.png").getImage();
                break;
            case "Battlepass13":
                ballImage = new ImageIcon("main/resources/images/btc-logo.png").getImage();
                backgroundImage = new ImageIcon("main/resources/images/Elon.png").getImage();
                break;
            case "Battlepass14":
                ballImage = new ImageIcon("main/resources/images/blue-ball.png").getImage();
                backgroundImage = new ImageIcon("main/resources/images/tiger-bg.png").getImage();
                break;
            case "Battlepass15":
                ballImage = new ImageIcon("main/resources/images/ltc-logo.png").getImage();
                backgroundImage = new ImageIcon("main/resources/images/Elon.png").getImage();
                break;
            case "Battlepass16":
                ballImage = new ImageIcon("main/resources/images/btc-logo.png").getImage();
                backgroundImage = new ImageIcon("main/resources/images/Elon.png").getImage();
                break;
            case "Battlepass17":
                ballImage = new ImageIcon("main/resources/images/blue-ball.png").getImage();
                backgroundImage = new ImageIcon("main/resources/images/tiger-bg.png").getImage();
                break;
            case "Battlepass18":
                ballImage = new ImageIcon("main/resources/images/ltc-logo.png").getImage();
                backgroundImage = new ImageIcon("main/resources/images/Elon.png").getImage();
                break;
            case "Battlepass19":
                ballImage = new ImageIcon("main/resources/images/btc-logo.png").getImage();
                backgroundImage = new ImageIcon("main/resources/images/Elon.png").getImage();
                break;
            case "Battlepass20":
                ballImage = new ImageIcon("main/resources/images/blue-ball.png").getImage();
                backgroundImage = new ImageIcon("main/resources/images/tiger-bg.png").getImage();
                break; */
            default:
                ballImage = new ImageIcon("main/resources/images/pink-ball.png").getImage();
                backgroundImage = new ImageIcon("main/resources/images/black-bg.jpg").getImage();
                break;
        }

        //setup platform
        platform.setSizes(Config.PLATFORM_WIDTH,25);
        platform.setPosition(Config.SCREEN_WIDTH/2 - platform.getSizeX()/2,(int)(Config.SCREEN_HEIGHT * 0.75));
        platform.setColor(Color.orange);
        platform.setSpeed(Config.PLATFORM_SPEED);

        //setup ball
        objects.balls[0].setSizes(25);
        objects.balls[0].setPosition(Config.SCREEN_WIDTH/2 - objects.balls[0].getRadius()/2, (int)(Config.SCREEN_HEIGHT * 0.75)- platform.getSizeY());
        objects.balls[0].setColor(Color.pink);

        //setup model
        model = new Model(height, length);
        brickCount = height * length;

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
            if(objects.ballCount() == 0){
                System.out.println("You dead as shit"); //Temporary
                //Frederik Tom Kronborg Paludan aka Palu aka Jeff aka Pookie aka mynamajeff på DTI
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
        //Starts balls[0] if balls[0] hasn't been started.
        if(keyH.aPressed || keyH.leftArrowPressed){
            if(!objects.balls[0].isStarted()){objects.balls[0].setVectorX(-objects.balls[0].getSpeed());
                objects.balls[0].setVectorY(-objects.balls[0].getSpeed()); objects.balls[0].start();}
            if(keyH.shiftPressed){
                platform.setX(platform.getX()- platform.getSpeed()*2);
            } else {
                platform.setX(platform.getX()- platform.getSpeed());
            }

        }

        //Checks if 'd' or right arrow is pressed. Platform goes right if d is pressed, goes right even faster if shift is also pressed.
        //Starts balls[0] if balls[0] hasn't been started.
        if(keyH.dPressed || keyH.rightArrowPressed){
            if(!objects.balls[0].isStarted()){objects.balls[0].setVectorX(objects.balls[0].getSpeed());
                objects.balls[0].setVectorY(-objects.balls[0].getSpeed()); objects.balls[0].start();}
            if(keyH.shiftPressed){
                platform.setX(platform.getX()+ platform.getSpeed()*2);
            } else {
                platform.setX(platform.getX()+ platform.getSpeed());
            }
        }
        platform.collision(Config.SCREEN_WIDTH);

        //Refactor ball code to allow multiple balls later on!
        for(int i = objects.ballCount() - 1; i >= 0; i--){
            score += objects.getBall(i).update(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT, platform, model.bricks, sound, objects);

            if(!objects.getBall(i).isLive()){
                objects.deleteBall(i);
            }
        }
        //Powerup code, checks if powerup has died, or if it has hit the platform. It it hits the platform, powerup is applied.
        for(int i = objects.powerupCount() - 1; i >= 0; i--){
            int powerupEnable = objects.getPowerup(i).update(platform);

            if(powerupEnable == 2){
                applyPowerup(objects.getPowerup(i).getChoice());
                objects.deletePowerup(i);
            } else if(!objects.getPowerup(i).isLive()){
                objects.deletePowerup(i);
            }
        }



    }

    //Standard method for drawing in JPanel
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Paint background
        if (backgroundImage != null) {
            g2.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
        }
        
        // Paint platform
        //If platform is flaming, add red outline.
        if(platform.isFlaming()){
            g2.setColor(Color.red);
            g2.fillRect(platform.getX()-3, platform.getY()-3,platform.getSizeX()+6,platform.getSizeY()+6);
        }

        g2.setColor(platform.getColor());
        g2.fillRect(platform.getX(), platform.getY(), platform.getSizeX(), platform.getSizeY());
        
        // Write score on platform
        g2.setColor(Color.black);
        g2.drawString(this.score + "", platform.getX() + 10, platform.getY() + platform.getSizeY() / 2);

        // Ball as image
        for (Ball ball : objects.balls) {
            if (ballImage != null) {
                g2.drawImage(ballImage, ball.getX(), ball.getY(), ball.getRadius(), ball.getRadius(), null);
            } else {
                // Fallback if image is not available
                g2.setColor(ball.getColor());
                g2.fillOval(ball.getX(), ball.getY(), ball.getRadius(), ball.getRadius());
            }
        }

        for (Powerup powerup : objects.powerups) {
            java.awt.Image icon = null;
            switch (powerup.getChoice()) {
                case 0: // Bomb
                    icon = bombIcon;
                    break;
                case 1: // Flame
                    icon = flameIcon;
                    break;
                case 2: // Plus
                    icon = plusIcon;
                    break;
            }
            
            g2.drawImage(icon, powerup.getX(), powerup.getY(), powerup.getSizeX(), powerup.getSizeY(), null);
        }
        

        // Bricks
        int brickCount = 0;
        for (Brick[] brickList : model.bricks) {
            for (Brick brick : brickList) {
                g2.setColor(brick.getColor());
                g2.fillRect(brick.getX(), brick.getY(), brick.getSizeX(), brick.getSizeY());
                if(brick.isLive()){brickCount++;}
            }
        }

        if(brickCount == 0){
            model.resetBricks();
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

    public void applyPowerup(int option){
        switch(option){
            case 3:
                System.out.println("Something sure happened!");
            //Adds ball
            case 2:
                Random rn = new Random();
                int direction = rn.nextInt(2);
                objects.addBall(new Ball());
                objects.balls[objects.ballCount()-1].setSizes(20);
                objects.balls[objects.ballCount()-1].setPosition(platform.getX() + platform.getSizeX() - objects.balls[0].getRadius()/2, (int)(Config.SCREEN_HEIGHT * 0.75)- platform.getSizeY());
                objects.balls[objects.ballCount()-1].setColor(Color.orange);
                objects.balls[objects.ballCount()-1].setVectorX((direction == 0 ? -1 : 1) * objects.balls[objects.ballCount()-1].getSpeed());
                objects.balls[objects.ballCount()-1].setVectorY(objects.balls[objects.ballCount()-1].getSpeed());

                break;
            //Sets ball aflame so it cuts through
            case 1:
                platform.setAflame();
                break;
             //Bomb - straight up ends game, deleting all bricks.
            case 0:
                for(Brick[] brickList : model.bricks){
                    for(Brick brick : brickList){
                        brick.destroyBrick();

                    }
                }
                break;

        }
    }
}