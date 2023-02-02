package me.alpha432.oyvey.features.modules.movement;

import me.alpha432.oyvey.features.modules.Module;

public class ReverseStep extends Module {
    public ReverseStep() {
        super("ReverseStep", "formerly known as fastfall", Category.MOVEMENT, true, false, false);
    }

    @Override
    public void onUpdate() {
        if (mc.player.onGround && !mc.player.inWater && !mc.player.isInLava()) mc.player.motionY -= 5.0;
    }
}

