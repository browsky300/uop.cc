package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;

public class Alpha extends Module {
    private static Alpha INSTANCE = new Alpha();
    
    public final Setting<Integer> items = this.register(new Setting<Integer>("Items", 0, 0, 16));
    public final Setting<Integer> obsidian = this.register(new Setting<Integer>("Obsidian", 0, 0, 255));
    
    public Alpha() {
        super("Alpha", "change the aspect ratio", Module.Category.RENDER, false, false, false);
        this.setInstance();
    }
    
    public static Alpha getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new Alpha();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

