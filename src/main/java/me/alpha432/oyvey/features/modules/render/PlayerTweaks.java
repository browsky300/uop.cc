package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;

public class PlayerTweaks extends Module {
    private static PlayerTweaks INSTANCE = new PlayerTweaks();
    
    public final Setting<Float> size = this.register(new Setting<Float>("Size", 1.0f, 0.0f, 1.0f));
    public final Setting<Boolean> nointerpolate = this.register(new Setting<Boolean>("NoInterpolate", false));
    public final Setting<Boolean> nolimbmove = this.register(new Setting<Boolean>("NoLimbMove", false));
    public final Setting<Boolean> sneak = this.register(new Setting<Boolean>("Sneak", false));
    public final Setting<Boolean> noarmor = this.register(new Setting<Boolean>("NoArmor", false));
    public final Setting<Boolean> nocape = this.register(new Setting<Boolean>("NoCape", false));
    public enum Skin {Off, Alex, Steve, Thunder};
    public Setting<Skin> skin = register(new Setting("Skin", Skin.Off));
    public final Setting<Boolean> throughWalls = this.register(new Setting<Boolean>("ThroughWalls", false));
    
    public PlayerTweaks() {
        super("PlayerTweaks", "change player model", Module.Category.RENDER, false, false, false);
        this.setInstance();
    }
    
    public static PlayerTweaks getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new PlayerTweaks();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

