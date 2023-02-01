package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;

public class AspectRatio extends Module {
    private static AspectRatio INSTANCE = new AspectRatio();
    
    public final Setting<Float> ratio = this.register(new Setting<Float>("Ratio", 1.3333f, 0.0f, 3.0f));
    
    public AspectRatio() {
        super("AspectRatio", "change the aspect ratio", Module.Category.RENDER, false, false, false);
        this.setInstance();
    }
    
    public static AspectRatio getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new AspectRatio();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
    
    @Override
    public String getDisplayInfo() {
        return String.valueOf(ratio.getValue());
    }
}

