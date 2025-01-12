package main.Model;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Dimension;

public class Model {
    public Brick[][] bricks;
    private int score;
    private int height;
    private int length;
    private Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    private int sizeX = (int) (size.getWidth() / 2.5 / 14 - 5);
    private int sizeY = 25;

    public Model(int height, int length){
        this.height = height;
        this.length = length;
        //Creates grid of Bricks
        this.bricks = new Brick[this.height][this.length];

        //Initializes each Brick
        for(int i = 0; i < this.height; i++){
            for(int j = 0; j < this.length; j++){
                this.bricks[i][j] = new Brick();
            }
        }

        //Fills with default setup (Wozniack like)
        fillBricks(0);
    }

    //Fills bricks with a predetermined setup
    public void fillBricks(int setup){
        switch(setup) {
            //case 0 is meant to look like Wozniacks original design
            case 0:
                for(int i = 0; i < this.bricks.length; i++){
                    for(int j = 0; j < this.bricks[0].length; j++){
                        this.bricks[i][j].setScore(7 - 2 * (i/2));
                        this.bricks[i][j].setPosition(j * (sizeX+5)+2, i * (sizeY+5)+2);
                        this.bricks[i][j].setSizes(sizeX,sizeY);
                        
                        switch(this.bricks[i][j].getScore()){
                            case 7:
                                this.bricks[i][j].setColor(Color.red);
                                break;
                            case 5:
                                this.bricks[i][j].setColor(Color.orange);
                                break;
                            case 3:
                                this.bricks[i][j].setColor(Color.green);
                                break;
                            case 1:
                                this.bricks[i][j].setColor(Color.yellow);
                                break;
                            
                        }
                        
                    }
                }
                break;

            //case 1 is an absolute base case
            case 1:
                for(int i = 0; i < this.bricks.length; i++){
                    for(int j = 0; j < this.bricks[0].length; j++){
                        this.bricks[i][j].setScore(1);
                        this.bricks[i][j].setPosition(i * sizeX, j * sizeY);
                        this.bricks[i][j].setSizes(sizeX,sizeY);
                        this.bricks[i][j].setColor(Color.black);
                    }
                }
        }
    }

    //Used for printing bricks - see scores of bricks etc. Broken bricks are described with 0.
    public void printBricks(){
        for(Brick[] i : this.bricks){
            for(Brick j : i){
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    //Get height
    public int getHeight(){
        return this.height;
    }

    //Get length
    public int getLength(){
        return this.length;
    }

    //Get sizeX
    public int getSizeX(){
        return this.sizeX;
    }

    //Get sizeY
    public int getSizeY(){
        return this.sizeY;
    }

    //Get specific brick
    public String getBrick(int i, int j){
        return this.bricks[i][j].toString();
    }

    public Brick[][] getBricks(){
        return this.bricks;
    }

    //See the remaining sum of a grid.
    public int remainingSum(){
        int sum = 0;
        for(Brick[] i : this.bricks){
            for(Brick j : i){
                sum+=j.getScore();
            }
        }
        return sum;
    }

    //Gets current score
    public int getScore(){
        return this.score;
    }
}