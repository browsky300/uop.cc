package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;

public class DeathAnimation extends Module {
    private static DeathAnimation INSTANCE = new DeathAnimation();
    public enum DeathMode {Glitchy, Heaven};
    public Setting<DeathMode> deathmode = register(new Setting("Mode", DeathMode.Glitchy));
    
    public DeathAnimation() {
        super("DeathAnimation", "Makes the death animation awesome", Module.Category.RENDER, false, false, false);
        this.setInstance();
    }

    public static DeathAnimation getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new DeathAnimation();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

