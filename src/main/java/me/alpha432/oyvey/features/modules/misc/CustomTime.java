package me.alpha432.oyvey.features.modules.misc;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.event.events.PacketEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.play.server.SPacketTimeUpdate;

public class CustomTime extends Module {
    public Setting<Integer> time = register(new Setting("Time", 18000, 0, 24000));
    
    public CustomTime() {
        super("CustomTime", "sets the time", Category.MISC, true, false, false);
    }

    @Override
    public void onUpdate() {
        if (mc.world != null) mc.world.setWorldTime(time.getValue());
    }
    
    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive event) {
        if (event.getPacket() instanceof SPacketTimeUpdate) event.setCanceled(true);
    }
    
    @Override
    public String getDisplayInfo() {
        return String.valueOf(time.getValue());
    }
} 
