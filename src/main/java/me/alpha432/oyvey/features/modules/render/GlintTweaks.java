package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;

public class GlintTweaks extends Module {
    private static GlintTweaks INSTANCE = new GlintTweaks();
    public Setting<Boolean> color = this.register(new Setting<Boolean>("Color", true));
    public Setting<Integer> red = this.register(new Setting<Integer>("Red", 255, 0, 255, v -> color.getValue()));
    public Setting<Integer> green = this.register(new Setting<Integer>("Green", 0, 0, 255, v -> color.getValue()));
    public Setting<Integer> blue = this.register(new Setting<Integer>("Blue", 0, 0, 255, v -> color.getValue()));
    public Setting<Double> speed = this.register(new Setting<Double>("Speed", 1.0d, 0.0d, 5.0d));
    
    public GlintTweaks() {
        super("GlintTweaks", "Changes the enchantment glint.", Module.Category.RENDER, false, false, false);
        this.setInstance();
    }

    public static GlintTweaks getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new GlintTweaks();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

