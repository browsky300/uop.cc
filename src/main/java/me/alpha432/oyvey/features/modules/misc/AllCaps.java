package me.alpha432.oyvey.features.modules.misc;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;

public class AllCaps extends Module {
    private static AllCaps INSTANCE = new AllCaps();
    Setting<Boolean> lowercase = register(new Setting("Lowercase", false));
    
    public AllCaps() {
        super("AllCaps", "mf doom", Module.Category.MISC, false, false, false);
        this.setInstance();
    }

    public String changeCaps(String text) {
        if (!isOn()) return text;
        if (!lowercase.getValue()) {
            return text.toUpperCase();
        } else {
            return text.toLowerCase();
        }
    }

    public static AllCaps getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new AllCaps();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

