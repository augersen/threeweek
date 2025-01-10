package main;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Config {
    // Gets resolution of screen
    private static final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

    // Public constants for screen width and height
    public static final int SCREEN_WIDTH = (int) (size.getWidth() / 2.5 - 7);
    public static final int SCREEN_HEIGHT = (int) size.getHeight();
}