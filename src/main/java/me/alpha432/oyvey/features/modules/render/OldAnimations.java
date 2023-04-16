package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;

public class OldAnimations extends Module {
    private static OldAnimations INSTANCE = new OldAnimations();
    
    public enum Mode {Good, Old, Full};
    public Setting<Mode> mode = register(new Setting("Mode", Mode.Good));
    
    public OldAnimations() {
        super("OldAnimations", "Old swing animations", Module.Category.RENDER, false, false, false);
        this.setInstance();
    }
    
    public void onUpdate() {
        if (mode.getValue() == Mode.Old && mc.entityRenderer.itemRenderer.prevEquippedProgressMainHand >= 0.9) {
            mc.entityRenderer.itemRenderer.equippedProgressMainHand = 1.0f;
            mc.entityRenderer.itemRenderer.itemStackMainHand = mc.player.getHeldItemMainhand();
        }
        if (mode.getValue() == Mode.Old && mc.entityRenderer.itemRenderer.prevEquippedProgressOffHand >= 0.9) {
            mc.entityRenderer.itemRenderer.equippedProgressOffHand = 1.0f;
            mc.entityRenderer.itemRenderer.itemStackOffHand = mc.player.getHeldItemOffhand();
        }
    }

    public static OldAnimations getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new OldAnimations();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
    
    @Override
    public String getDisplayInfo() {
        return String.valueOf(mode.getValue());
    }
}

