package me.alpha432.oyvey.features.modules.combat;

import me.alpha432.oyvey.features.modules.Module;

public class AntiInvis extends Module {
    private static AntiInvis INSTANCE = new AntiInvis();
    
    public AntiInvis() {
        super("AntiInvis", "Shows invisible players", Module.Category.COMBAT, false, false, false);
        this.setInstance();
    }

    public static AntiInvis getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new AntiInvis();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

