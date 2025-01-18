package main;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Config {
    // Gets resolution of screen
    private static final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

    // Public constants for screen width and height
    public static final int SCREEN_WIDTH = 639;
    public static final int SCREEN_HEIGHT = (int) size.getHeight();
    public static final int FPS = 60;
    public static final int BRICK_HEIGHT = 8;
    public static final int BRICK_LENGTH = 14;

    //Game Modifiers
    public static int PLATFORM_SPEED = 6;
    public static int BALL_RADIUS = 25;
    public static int PLATFORM_WIDTH = 100;

    //modifierstates
    public static boolean POWERUPS_ENABLED = false;
    public static boolean PLATFORM_MODIFIER_ENABLED = false;
    public static boolean EXAMPLE_MODIFIER_ENABLED = false;
    public static boolean PLACEHOLDER_ENABLED = false;
    public static boolean INFINITE_ENABLED = false;
}