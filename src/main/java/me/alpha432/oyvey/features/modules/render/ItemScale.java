package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;

public class ItemScale extends Module {
    private static ItemScale INSTANCE = new ItemScale();
    
    public Setting<Float> X = this.register(new Setting<Float>("X", Float.valueOf(1.0f), Float.valueOf(0.0f), Float.valueOf(2.0f)));
    public Setting<Float> Y = this.register(new Setting<Float>("Y", Float.valueOf(1.0f), Float.valueOf(0.0f), Float.valueOf(2.0f)));
    public Setting<Float> Z = this.register(new Setting<Float>("Z", Float.valueOf(1.0f), Float.valueOf(0.0f), Float.valueOf(2.0f)));
    
    public ItemScale() {
        super("ItemScale", "Changes the item scale", Module.Category.RENDER, false, false, false);
        this.setInstance();
    }

    public static ItemScale getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new ItemScale();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

