package me.alpha432.oyvey.features.modules.misc;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.command.Command;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.util.Timer;
import me.alpha432.oyvey.event.events.PacketEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class PacketLogger extends Module {
    public Setting<Boolean> outgoing = this.register(new Setting<Boolean>("CPackets", false, "outgoing packets"));
    public Setting<Boolean> incoming = this.register(new Setting<Boolean>("SPackets", false, "incoming packets"));
    public List<Long> times = new ArrayList<Long>();

    public PacketLogger() {
        super("PacketLogger", "prints packets in chat", Category.MISC, true, false, false);
    }
    
    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send event) {
        if (outgoing.getValue()) Command.sendSilentMessage("\u21e7 " + event.getPacket().getClass().getSimpleName());
        times.add(System.currentTimeMillis());
    }
    
    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive event) {
        if (incoming.getValue()) Command.sendSilentMessage("\u21e9 " + event.getPacket().getClass().getSimpleName());
    }
    
    @Override
    public void onUpdate() {
        for (int i = 0; i < times.size(); i++) {
            if (times.get(i) + 1000 < System.currentTimeMillis()) {
                times.remove(i);
                i--;
            }
        }
    }
            
    
    @Override
    public String getDisplayInfo() {
        return String.valueOf(times.size());
    }
}
