package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.command.Command;

public class OldAnimations extends Module {

    public OldAnimations() {
        super("OldAnimations", "old swing animations", Category.RENDER, true, false, false);
    }

    @Override
    public void onUpdate() {
        if (mc.entityRenderer.itemRenderer.prevEquippedProgressMainHand >= 0.9) {
            mc.entityRenderer.itemRenderer.equippedProgressMainHand = 1.0f;
            mc.entityRenderer.itemRenderer.itemStackMainHand = mc.player.getHeldItemMainhand();
        }
        if (mc.entityRenderer.itemRenderer.prevEquippedProgressOffHand >= 0.9) {
            mc.entityRenderer.itemRenderer.equippedProgressOffHand = 1.0f;
            mc.entityRenderer.itemRenderer.itemStackOffHand = mc.player.getHeldItemOffhand();
        }
    }
} 
