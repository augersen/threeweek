package main.Model;

import main.Config;

import java.awt.Color;
import java.util.Random;

/**
 * Bricks that ball can destroy. Gives a score. Mostly used in a 2d array.
 * Makes sound!
 */
public class Brick extends Entity {
    private int score;
    private boolean live;

    public Brick(){
        super();
        this.score = 0;
        this.live = true;
    }

    //Returns score as a string
    @Override
    public String toString(){
        return this.score + super.toString();
    }

    //Returns score as an int
    public int getScore(){
        return this.score;
    }

    //Sets score to be used in Model
    public void setScore(int score){
        this.score = score;
    }

    //Moves brick off screen, available to fetch when resetting round.
    public void destroyBrick(){
        this.setPosition(this.getX()- Config.SCREEN_WIDTH,this.getY());
        this.live = false;
    }

    public boolean isLive(){
        return live;
    }

    public void setLive(boolean live){
        this.live = live;
    }
}
