package me.alpha432.oyvey.features.modules.player;

import me.alpha432.oyvey.features.modules.Module;

public class GMSwitcher extends Module {

    public GMSwitcher() {
        super("GMSwitcher", "changes your gamemode", Category.PLAYER, true, false, false);
    }

    @Override
    public void onEnable() {
        mc.player.sendChatMessage("/gamemode " + (mc.player.isCreative() ? "0" : "1"));
        this.disable();
    }
} 
