package me.alpha432.oyvey.features.modules.combat;

import me.alpha432.oyvey.features.modules.Module;

public class AntiUnicode extends Module {
    private static AntiUnicode INSTANCE = new AntiUnicode();
    
    public AntiUnicode() {
        super("AntiUnicode", "Blocks unicode", Category.COMBAT, false, false, false);
        this.setInstance();
    }

    public static AntiUnicode getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new AntiUnicode();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

