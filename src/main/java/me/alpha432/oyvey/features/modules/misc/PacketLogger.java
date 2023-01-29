package me.alpha432.oyvey.features.modules.misc;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.command.Command;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.event.events.PacketEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PacketLogger extends Module {
    public Setting<Boolean> incoming = this.register(new Setting<Boolean>("Incoming", Boolean.valueOf(false), "s packets"));
    public Setting<Boolean> outgoing = this.register(new Setting<Boolean>("Outgoing", Boolean.valueOf(false), "c packets"));

    public PacketLogger() {
        super("PacketLogger", "prints packets in chat", Category.MISC, true, false, false);
    }
    
    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send event) {
        if (outgoing.getValue()) Command.sendSilentMessage("⇧ " + event.getPacket().getClass().getSimpleName());
    }
    
    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive event) {
        if (incoming.getValue()) Command.sendSilentMessage("⇩ " + event.getPacket().getClass().getSimpleName());
    }
}
