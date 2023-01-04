package me.alpha432.oyvey.features.modules.player;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;

public class Swing extends Module {
    private static Swing INSTANCE = new Swing();
    
    public enum SwingMode {Mainhand, Offhand, Offhandc, None}
    public Setting<SwingMode> swingmode = register(new Setting("Mode", SwingMode.Mainhand));
    
    public Swing() {
        super("Swing", "options for swinging", Module.Category.PLAYER, false, false, false);
        this.setInstance();
    }

    public static Swing getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new Swing();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
    
    @Override
    public String getDisplayInfo() {
        return this.swingmode.currentEnumName();
    }
}

