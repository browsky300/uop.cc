package me.alpha432.oyvey.features.modules.client;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.features.command.Command;



import me.alpha432.oyvey.event.events.PacketEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.status.client.CPacketPing;
import net.minecraft.network.status.server.SPacketPong;
import net.minecraft.network.handshake.client.C00Handshake;


import net.minecraft.network.play.client.CPacketKeepAlive;
import net.minecraft.network.play.server.SPacketKeepAlive;

public class Debug extends Module {
    private static Debug INSTANCE = new Debug();
    public long pingSentAt = 0;
    
    public Debug() {
        super("Debug", "Debug", Category.CLIENT, false, false, false);
        this.setInstance();
    }






    //for use by other modules/mixins
    public static Debug getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new Debug();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

