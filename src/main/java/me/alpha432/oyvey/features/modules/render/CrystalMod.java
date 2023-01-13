package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;

public class CrystalMod extends Module {
    private static CrystalMod INSTANCE = new CrystalMod();
    
    public final Setting<Float> size = this.register(new Setting<Float>("Size", Float.valueOf(1.0f), Float.valueOf(0.0f), Float.valueOf(1.0f)));
    public final Setting<Float> speed = this.register(new Setting<Float>("Speed", Float.valueOf(1.0f), Float.valueOf(0.0f), Float.valueOf(3.0f)));
    public final Setting<Float> oscillate = this.register(new Setting<Float>("Oscillate", Float.valueOf(1.0f), Float.valueOf(0.0f), Float.valueOf(3.0f)));
    
    public CrystalMod() {
        super("CrystalMod", "Changes size and speed of crystals.", Module.Category.RENDER, false, false, false);
        this.setInstance();
    }

    public static CrystalMod getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new CrystalMod();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

