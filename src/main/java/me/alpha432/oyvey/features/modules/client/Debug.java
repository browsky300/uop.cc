package me.alpha432.oyvey.features.modules.client;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.features.command.Command;

public class Debug extends Module {
    private static Debug INSTANCE = new Debug();
    
    public Debug() {
        super("Debug", "Debug", Category.CLIENT, false, false, false);
        this.setInstance();
    }

    public static Debug getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new Debug();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

