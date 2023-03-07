package me.alpha432.oyvey.features.modules.player;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.event.events.PacketEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.play.server.SPacketTimeUpdate;

public class AntiPickup extends Module {
    
    public AntiPickup() {
        super("AntiPickup", "prevents you from picking up items", Category.PLAYER, true, false, false);
    }
    
    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive event) {
        if (event.getPacket() instanceof SPacketTimeUpdate) event.setCanceled(true);
    }
} 
