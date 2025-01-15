package main;

import main.Model.Ball;
import main.Model.Powerup;

/**
 * Custom ArrayList class made because ArrayList wasn't cooperating.
 * Has option to get, set, add and delete values.
 */
public class AugustArray {
    Ball[] balls;
    Powerup[] powerups;
    public AugustArray(){
        this.balls = new Ball[]{new Ball()};

        this.powerups = new Powerup[0];
    }

    public Ball getBall(int index){
        return balls[index];
    }

    public void setBall(int index, Ball ball){
        this.balls[index] = ball;
    }

    public Powerup getPowerup(int index) {
        return powerups[index];
    }

    public void setPowerup(int index, Powerup powerup) {
        this.powerups[index] = powerup;
    }

    public void addBall(Ball value){
        Ball[] newBalls = new Ball[this.balls.length + 1];
        for(int i = 0; i < this.balls.length; i++){
            newBalls[i] = this.balls[i];
        }
        newBalls[this.balls.length] = value;
        this.balls = newBalls;
    }

    public void addPowerup(Powerup value){
        Powerup[] newPowerups = new Powerup[this.powerups.length + 1];
        for(int i = 0; i < this.powerups.length; i++){
            newPowerups[i] = this.powerups[i];
        }
        newPowerups[this.powerups.length] = value;
        this.powerups = newPowerups;
    }

    public void deleteBall(int index){
        Ball[] newArray = new Ball[this.balls.length - 1];
        for(int i = 0; i < index; i++){
            newArray[i] = this.balls[i];
        }
        for(int i = index; i < this.balls.length - 1; i++){
            newArray[i] = this.balls[i + 1];
        }
        this.balls = newArray;
    }

    public void deletePowerup(int index){
        Powerup[] newArray = new Powerup[this.powerups.length - 1];
        for(int i = 0; i < index; i++){
            newArray[i] = this.powerups[i];
        }
        for(int i = index; i < this.powerups.length - 1; i++){
            newArray[i] = this.powerups[i + 1];
        }
        this.powerups = newArray;
    }
}
