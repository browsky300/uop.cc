package me.alpha432.oyvey.features.modules.player;

import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.network.play.client.CPacketEntityAction;

public class ElytraDeploy extends Module {

    public ElytraDeploy() {
        super("ElytraDeploy", "deploys the elytra (no way)", Category.PLAYER, true, false, false);
    }

    @Override
    public void onEnable() {
        mc.player.connection.sendPacket(new CPacketEntityAction(mc.player, CPacketEntityAction.Action.START_FALL_FLYING));
        this.disable();
    }
} 
