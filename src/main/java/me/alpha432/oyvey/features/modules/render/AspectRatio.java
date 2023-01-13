package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;

public class AspectRatio extends Module {
    private static AspectRatio INSTANCE = new AspectRatio();
    
    public final Setting<Float> ratio = this.register(new Setting<Float>("Ratio", Float.valueOf(1.0f), Float.valueOf(0.0f), Float.valueOf(1.0f)));
    
    public AspectRatio() {
        super("AspectRatio", "IT DOESNT CHANGE YOUR GAME WHEN ITS OFF YOURE GOING INSANE DBEAR", Module.Category.RENDER, false, false, false);
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
}

