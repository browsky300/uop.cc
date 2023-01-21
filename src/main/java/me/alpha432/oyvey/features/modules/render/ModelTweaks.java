package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;

public class ModelTweaks extends Module {
    private static ModelTweaks INSTANCE = new ModelTweaks();
    
    public final Setting<Float> csize = this.register(new Setting<Float>("CrystalSize", Float.valueOf(1.0f), Float.valueOf(0.0f), Float.valueOf(1.0f)));
    public final Setting<Float> cspeed = this.register(new Setting<Float>("CrystalSpeed", Float.valueOf(1.0f), Float.valueOf(0.0f), Float.valueOf(3.0f)));
    public final Setting<Float> coscillate = this.register(new Setting<Float>("CrystalOscillate", Float.valueOf(1.0f), Float.valueOf(0.0f), Float.valueOf(3.0f)));
    public final Setting<Float> psize = this.register(new Setting<Float>("PlayerSize", Float.valueOf(1.0f), Float.valueOf(0.0f), Float.valueOf(1.0f)));
    
    public ModelTweaks() {
        super("ModelTweaks", "change model properties", Module.Category.RENDER, false, false, false);
        this.setInstance();
    }

    public static ModelTweaks getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new ModelTweaks();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

