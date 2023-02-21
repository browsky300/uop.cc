package me.alpha432.oyvey.features.modules.misc;

import me.alpha432.oyvey.features.modules.Module;

public class AutoJewbase extends Module {

    public AutoJewbase() {
        super("AutoJewbase", "omg its johnmcswag", Category.MISC, true, false, false);
    }

    @Override
    public void onEnable() {
        mc.player.sendChatMessage("Welcome to jewbase 4 we have been basing for a week now and we decided that you could join the coords are -8043809 -203912");
        this.disable();
    }
} 
