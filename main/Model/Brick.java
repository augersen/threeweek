package main.Model;

import java.awt.Color;

/**
 * Bricks that ball can destroy. Gives a score. Mostly used in a 2d array.
 * Makes sound!
 */
public class Brick extends Entity {
    private int score;

    public Brick(){
        super();
        this.score = 0;
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

    public void destroyBrick(){
        this.setPosition(-50,-50);
        this.setSizes(0,0);
        this.setColor(Color.black);
        this.setScore(0);
    }
}
