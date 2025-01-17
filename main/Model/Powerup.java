package main.Model;

import main.Config;

import java.awt.*;
import java.util.Random;

public class Powerup extends Entity {
    int choice;
    boolean live = true;
    public Powerup(int x, int y){
        super();
        this.setX(x);
        this.setY(y);
        this.setSizes(Config.BALL_RADIUS, Config.BALL_RADIUS);


        Random random = new Random();
        this.choice = random.nextInt(4);
        switch(this.choice){
            case 0:
            this.setColor(Color.black);
            break;
            case 1:
            this.setColor(Color.red);
            break;
            case 2:
            this.setColor(Color.green);
            break;
            case 3:
            this.setColor(Color.yellow);
            break;
        }


    }

    public boolean isLive(){
        return live;    }

    public int getChoice(){
        return choice;
    }

    public int update(Platform platform){
        int x = this.getX();
        int y = this.getY();
        int size = this.getSizeY();

        this.setY(this.getY() + 2);
        if(this.getY() > Config.SCREEN_HEIGHT){
            this.live = false;
            return 1;
        }
        if(y < platform.getY() + platform.getSizeY() + size && y > platform.getY() - size &&
                x < platform.getX() + platform.getSizeX() + size && x > platform.getX() - size){
            return 2;
        }
        return 0;
    }

}
