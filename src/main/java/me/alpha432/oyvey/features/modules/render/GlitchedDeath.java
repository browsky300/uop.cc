package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.features.modules.Module;

public class GlitchedDeath extends Module {
    private static GlitchedDeath INSTANCE = new GlitchedDeath();
    
    public GlitchedDeath() {
        super("GlitchedDeath", "Makes the death animation awesome", Module.Category.RENDER, false, false, false);
        this.setInstance();
    }

    public static GlitchedDeath getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new GlitchedDeath();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

