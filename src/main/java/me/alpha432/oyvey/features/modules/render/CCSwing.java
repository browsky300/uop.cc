package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;

public class CCSwing extends Module {
    private static CCSwing INSTANCE = new CCSwing();
    
    public Setting<Float> progress = this.register(new Setting<Float>("Progress", 0.0f, 0.0f, 1.0f));
    
    public CCSwing() {
        super("CCSwing", "cool constantiam swing :D", Module.Category.RENDER, false, false, false);
        this.setInstance();
    }
    
    public static CCSwing getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new CCSwing();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
    
    @Override
    public String getDisplayInfo() {
        return String.valueOf(progress.getValue());
    }
}

