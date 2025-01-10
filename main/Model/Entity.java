package main.Model;

import java.awt.Color;

/**
 * Entity class is used as a standard for the entities in the game, Ball, Brick and Platform. It is made to be changed with the Controller
 * and shown in View. Changes are made in the Model class.
 */
public class Entity {
    private int x;
    private int y;
    private int sizeX;
    private int sizeY;
    private Color color;
    
    //Standard initialization for Entity objects
    public Entity(){
        this.x = 0;
        this.y = 0;
        this.sizeX = 0;
        this.sizeY = 0;
        this.color = Color.black;
        
    }

    //Sets position to be used in View.
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    //Sets vertical and horizontal size of entity
    public void setSizes(int sizeX, int sizeY){
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    //Sets color to be used in View
    public void setColor(Color color){
        this.color = color;
    }

    //Returns a blank string
    public String toString(){
        return "";
    }

    //Returns x coordinate as an int
    public int getX(){
        return this.x;
    }

    //Returns y coordinate as an int
    public int getY(){
        return this.y;
    }

    //Returns sizeX as an int
    public int getSizeX(){
        return this.sizeX;
    }

    //Returns sizeY as an int
    public int getSizeY(){
        return this.sizeY;
    }

    //Return color as a Color
    public Color getColor(){
        return this.color;
    }

    //Set x value
    public void setX(int x){
        this.x = x;
    }

    //Set y value
    public void setY(int y){
        this.y = y;
    }
}
