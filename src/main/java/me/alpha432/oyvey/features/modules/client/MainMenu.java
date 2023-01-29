package me.alpha432.oyvey.features.modules.client;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;

public class MainMenu extends Module {
    private static MainMenu INSTANCE = new MainMenu();
    
    public Setting<Boolean> customsplash = register(new Setting("Custom Splash", true));
    public Setting<String> splashtext = register(new Setting("Text", "the rotations dont conflict its in your head", v -> customsplash.getValue()));
    
    public MainMenu() {
        super("MainMenu", "Tweaks to the main menu", Module.Category.CLIENT, false, false, false);
        this.enabled.setValue(true);
        this.setInstance();
    }

    public static MainMenu getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new MainMenu();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

