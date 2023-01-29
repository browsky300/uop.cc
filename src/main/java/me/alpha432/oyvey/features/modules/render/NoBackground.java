package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.features.modules.Module;

public class NoBackground extends Module {
    private static NoBackground INSTANCE = new NoBackground();
    
    public NoBackground() {
        super("NoBackground", "removes minecraft background", Module.Category.RENDER, false, false, false);
        this.setInstance();
    }

    public static NoBackground getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new NoBackground();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

