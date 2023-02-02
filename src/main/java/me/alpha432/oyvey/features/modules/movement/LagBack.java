package me.alpha432.oyvey.features.modules.movement;

import me.alpha432.oyvey.features.modules.Module;

public class LagBack extends Module {
    public LagBack() {
        super("LagBack", "rubberbands you", Category.MOVEMENT, true, false, false);
    }

    @Override
    public void onEnable() {
        mc.player.motionY = 1;
        this.disable();
    }
} 
