package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;

public class CrystalTweaks extends Module {
    private static CrystalTweaks INSTANCE = new CrystalTweaks();
    
    public final Setting<Float> size = this.register(new Setting<Float>("Size", Float.valueOf(1.0f), Float.valueOf(0.0f), Float.valueOf(1.0f)));
    public final Setting<Float> speed = this.register(new Setting<Float>("Speed", Float.valueOf(1.0f), Float.valueOf(0.0f), Float.valueOf(3.0f)));
    public final Setting<Float> oscillate = this.register(new Setting<Float>("Oscillate", Float.valueOf(1.0f), Float.valueOf(0.0f), Float.valueOf(3.0f)));
    
    public CrystalTweaks() {
        super("CrystalTweaks", "change crystal model", Module.Category.RENDER, false, false, false);
        this.setInstance();
    }

    public static CrystalTweaks getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new CrystalTweaks();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

