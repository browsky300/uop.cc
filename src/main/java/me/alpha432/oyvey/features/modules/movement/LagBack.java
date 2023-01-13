package me.alpha432.oyvey.features.modules.movement;

import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.network.play.client.CPacketPlayer;

public class LagBack extends Module {

    public LagBack() {
        super("LagBack", "rubberbands you", Category.MOVEMENT, true, false, false);
    }

    @Override
    public void onEnable() {
        mc.player.connection.sendPacket(new CPacketPlayer.Position(-8043809d, 64d, -203912d, false));
        this.disable();
    }
} 
