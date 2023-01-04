package me.alpha432.oyvey.features.modules.combat;

import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.network.play.client.CPacketCloseWindow;

public class AntiDesyncC extends Module {

    public AntiDesyncC() {
        super("AntiDesync-C", "constlagiam", Category.COMBAT, true, false, false);
    }

    @Override
    public void onEnable() {
        mc.player.connection.sendPacket(new CPacketCloseWindow(0));
        this.disable();
    }
} 
