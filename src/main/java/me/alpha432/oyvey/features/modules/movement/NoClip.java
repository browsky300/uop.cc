package me.alpha432.oyvey.features.modules.movement;

import me.alpha432.oyvey.features.modules.Module;

public class NoClip extends Module {
    
    public NoClip() {
        super("NoClip", "sv_cheats 1", Category.MOVEMENT, true, false, false);
    }

    @Override
    public void onUpdate() {
        mc.player.noClip = true;
    }
}
