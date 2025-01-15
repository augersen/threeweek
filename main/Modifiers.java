package main;

public class Modifiers {

    public Modifiers() {
    }

    //Test modifiers
    public void exampleModifier() {
        System.out.println("Example modifier");
    }
    public void disableExampleModifier() {
        System.out.println("Disable example modifier");
    }

    //modfications to platform
    public void platformModifier() {
        System.out.println("Platform modifier");
        Config.PLATFORM_SPEED += 6;
        Config.PLATFORM_WIDTH -= 50;
    }
    public void platformDisableModifier() {
        System.out.println("Platform modifier disabled");
        Config.PLATFORM_SPEED -= 6;
        Config.PLATFORM_WIDTH += 50;
    }
}
