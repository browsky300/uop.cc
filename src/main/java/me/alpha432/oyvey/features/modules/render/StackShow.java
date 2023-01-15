package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.features.modules.Module;

public class StackShow extends Module {
    private static StackShow INSTANCE = new StackShow();
    
    public StackShow() {
        super("StackShow", "idk how to explain this", Module.Category.RENDER, false, false, false);
        this.setInstance();
    }

    public static StackShow getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new StackShow();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

