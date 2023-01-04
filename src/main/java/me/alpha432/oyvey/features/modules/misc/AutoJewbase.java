package me.alpha432.oyvey.features.modules.misc;

import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.network.play.client.CPacketChatMessage;

public class AutoJewbase extends Module {

    public AutoJewbase() {
        super("AutoJewbase", "omg its johnmcswag", Category.MISC, true, false, false);
    }

    @Override
    public void onEnable() {
        if (fullNullCheck()) return;
        mc.player.connection.sendPacket(new CPacketChatMessage("Welcome to jewbase 4 we have been basing for a week now and we decided that you could join the coords are -8043809 -203912"));
        this.disable();
    }
} 
