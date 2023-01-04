package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.features.modules.Module;

public class NoSway extends Module {
    private static NoSway INSTANCE = new NoSway();
    
    public NoSway() {
        super("NoSway", "Disables the swaying animation", Module.Category.RENDER, false, false, false);
        this.setInstance();
    }

    public static NoSway getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new NoSway();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

