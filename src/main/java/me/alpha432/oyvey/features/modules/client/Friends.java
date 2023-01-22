package me.alpha432.oyvey.features.modules.client;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;

public class Friends extends Module {
    private static Friends INSTANCE = new Friends();
    
    public Friends() {
        super("Friends", "friend settings", Module.Category.CLIENT, false, false, false);
        this.setInstance();
    }

    public static Friends getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new Friends();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

