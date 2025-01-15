package main;

public class Modifiers {

    private static Modifiers instance;


    //
    private Modifiers() {}

    // Get the single instance of Modifiers - This will allow the modifiers to remain active until reset
    public static Modifiers getInstance() {
        if (instance == null) {
            instance = new Modifiers();
        }
        return instance;
    }

    // Reset the state of all modifiers
    public void reset() {
        Config.EXAMPLE_MODIFIER_ENABLED = false;
        Config.PLATFORM_MODIFIER_ENABLED = false;
        System.out.println("Modifiers reset to default state.");
    }

    // Enable/disable ExampleModifier
    public void exampleModifier() {
        Config.EXAMPLE_MODIFIER_ENABLED = true;
        System.out.println("Example modifier enabled");
    }

    public void disableExampleModifier() {
        Config.EXAMPLE_MODIFIER_ENABLED = false;
        System.out.println("Example modifier disabled");
    }


    // Enable/disable PlatformModifier
    public void platformModifier() {
        Config.PLATFORM_MODIFIER_ENABLED = true;
        Config.PLATFORM_SPEED += 6;
        Config.PLATFORM_WIDTH -= 50;
    }

    public void platformDisableModifier() {
        Config.PLATFORM_MODIFIER_ENABLED = false;
        Config.PLATFORM_SPEED -= 6;
        Config.PLATFORM_WIDTH += 50;
    }

    //Enable/disable powerupmodifier
    public void powerupModifier(){
        System.out.println("Powerup modifier");
        Config.POWERUPS_ENABLED = true;
    }

    public void powerupDisableModifier(){
        System.out.println("Powerup modifier disabled");
        Config.POWERUPS_ENABLED = false;
    }

    //Enable/disbale placeholderModifier
    public void placeholderModifier(){
        System.out.println("Placeholder modifier");
        Config.PLACEHOLDER_ENABLED = true;
    }
    public void placeholderDisableModifier(){
        System.out.println("Placeholder modifier disabled");
        Config.PLACEHOLDER_ENABLED = false;
    }

    // Get current active modifiers as a string
    public String getCurrentModifier() {
        StringBuilder currentModifier = new StringBuilder();

        if (Config.EXAMPLE_MODIFIER_ENABLED) {
            currentModifier.append("ExampleModifier");
        }
        if (Config.PLATFORM_MODIFIER_ENABLED) {
            currentModifier.append("PlatformModifier");
        }
        if (Config.POWERUPS_ENABLED) {
            currentModifier.append("PowerupModifier");
        }
        if (Config.PLACEHOLDER_ENABLED) {
            currentModifier.append("PlaceholderModifier");
        }

        return currentModifier.length() > 0 ? currentModifier.toString() : "NoModifier";
    }
}
