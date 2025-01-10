package main.Model;

public class Ball extends Entity {
    private int vectorX;
    private int vectorY;
    private int speed;
    private int radius;

    public Ball() {
        super();
        this.vectorX = 1;
        this.vectorY = -1;
        this.speed = 0;
    }

    // Sets size (singular instead of plural, as a ball only has a radius, not an x
    // and y)
    public void setSizes(int radius) {
        this.radius = radius;
    }

    // Returns vectorX as an int
    public int getVectorX() {
        return this.vectorX;
    }

    // Returns vectorY as an int
    public int getVectorY() {
        return this.vectorY;
    }

    // Set vectorX
    public void setVectorX(int vectorX) {
        this.vectorX = vectorX;
    }

    // Set vectorY
    public void setVectorY(int vectorY) {
        this.vectorY = vectorY;
    }

    // Returns radius as an int
    public int getRadius() {
        return this.radius;
    }

    // Returns speed as an int
    public int getSpeed() {
        return this.speed;
    }

    // Sets speed
    public void setSpeed(int speed) {
        this.speed = speed;
        this.vectorX = this.vectorX / Math.abs(this.vectorX) * speed;
        this.vectorY = this.vectorY / Math.abs(this.vectorY) * speed;
    }

    // Returns string with speed
    @Override
    public String toString() {
        return this.speed + super.toString();
    }

    public int update(int screenWidth, int screenHeight, Platform platform, Brick[][] bricks, Sound sound) {
        super.setX(this.getX() + this.vectorX);
        super.setY(this.getY() + this.vectorY);

        int x = this.getX();
        int y = this.getY();
        int size = this.radius / 2;
        int score = 0;

        if (x < 0 || x > screenWidth - size * 2) {
            this.vectorX *= -1;
        }

        if (y < 0) {
            this.vectorY *= -1;
        }

        if (y < platform.getY() + platform.getSizeY() + size && y > platform.getY() - size &&
                x < platform.getX() + platform.getSizeX() + size && x > platform.getX() - size) {
            this.collision(platform.getX(), platform.getY(), platform.getSizeX(), platform.getSizeY());
        }

        for (Brick[] brickList : bricks) {
            for (Brick brick : brickList) {
                if (y < brick.getY() + brick.getSizeY() + size && y > brick.getY() - size &&
                        x < brick.getX() + brick.getSizeX() + size && x > brick.getX() - size) {
                    score += this.collision(brick.getX(), brick.getY(), brick.getSizeX(), brick.getSizeY(), brick,
                            sound);
                }
            }
        }

        return score;
    }

    // For bricks
    public int collision(int entityX, int entityY, int entitySizeX, int entitySizeY, Brick brick, Sound sound) {
        int action = 0;
        int size = this.radius / 2;
        int score = 0;

        int[] ballPos1 = new int[] { this.getX() + this.radius / 2, this.getY(), 1 };
        int[] ballPos2 = new int[] { this.getX(), this.getY() + this.radius / 2, 2 };
        int[] ballPos3 = new int[] { this.getX() + this.radius / 2, this.getY() + this.radius / 2, 3 };
        int[] ballPos4 = new int[] { this.getX() + this.radius / 2, this.getY() + this.radius, 4 };

        int[][] ballPositions = new int[][] { ballPos1, ballPos2, ballPos3, ballPos4 };

        outerloop: for (int[] pos : ballPositions) {
            if (pos[1] < entityY + entitySizeY && pos[1] > entityY &&
                    pos[0] < entityX + entitySizeX && pos[0] > entityX) {
                action = pos[2];
                break outerloop;
            }
        }

        switch (action) {
            case 4 -> this.vectorY = -1 * Math.abs(this.vectorY);
            case 2 -> this.vectorX = Math.abs(this.vectorX);
            case 3 -> this.vectorX = -1 * Math.abs(this.vectorX);
            case 1 -> this.vectorY = Math.abs(this.vectorY);
        }

        if (action != 0) {
            score = brick.getScore();
            brick.destroyBrick();
            sound.playSE(2);
        }

        return score;

    }

    // For player entity
    public void collision(int entityX, int entityY, int entitySizeX, int entitySizeY) {
        int action = 0;
        int size = this.radius / 2;

        int[] ballPos1 = new int[] { this.getX() + this.radius / 2, this.getY(), 1 };
        int[] ballPos2 = new int[] { this.getX(), this.getY() + this.radius / 2, 2 };
        int[] ballPos3 = new int[] { this.getX() + this.radius / 2, this.getY() + this.radius / 2, 3 };
        int[] ballPos4 = new int[] { this.getX() + this.radius / 2, this.getY() + this.radius, 4 };

        int[][] ballPositions = new int[][] { ballPos1, ballPos2, ballPos3, ballPos4 };

        for (int[] pos : ballPositions) {
            if (pos[1] < entityY + entitySizeY + size && pos[1] > entityY - size &&
                    pos[0] < entityX + entitySizeX + size && pos[0] > entityX - size) {
                action = pos[2];
            }
        }

        switch (action) {
            case 2 -> this.vectorX = Math.abs(this.vectorX);
            case 3 -> this.vectorX = -1 * Math.abs(this.vectorX);
            case 1 -> this.vectorY = Math.abs(this.vectorY);
        }

        // math for making ball bounce, makes no sense.
        if (action == 4) {
            int midpoint = entityX + entitySizeX / 2;
            if (this.getX() < midpoint) {
                this.vectorX = -1 * Math.abs(this.vectorX);
            } else {
                this.vectorX = Math.abs(this.vectorX);
            }
            this.vectorY = -1 * Math.abs(this.vectorY);
        }
    }
}
