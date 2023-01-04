package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;

public class CustomSkins extends Module {
    private static CustomSkins INSTANCE = new CustomSkins();
    public enum Skin {Alex, Steve, Thunder};
    public Setting<Skin> skin = register(new Setting("Skin", Skin.Alex));
    
    public CustomSkins() {
        super("CustomSkins", "overrides everyone's skins", Module.Category.RENDER, false, false, false);
        this.setInstance();
    }

    public static CustomSkins getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new CustomSkins();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

