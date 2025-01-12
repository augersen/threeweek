package main.Model;

/**
 * Platform is the player controlled platform near the bottom of the screen.
 */
public class Platform extends Entity {
    private int speed;

    public Platform(){
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
