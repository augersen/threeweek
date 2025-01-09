package main.Model;

public class Player extends Entity {
    private int speed;

    public Player(){
        super();
        this.speed = 0;
    }
    
    public int getSpeed(){
        return this.speed;
    }

    //Sets speed
    public void setSpeed(int speed){
        this.speed = speed;
    }

    public void collision(int screenWidth){
        if(this.getX() < 0){
            this.setX(0);
        } else if(this.getX() + this.getSizeX() > screenWidth){
            this.setX(screenWidth - this.getSizeX());
        }
    }
}
