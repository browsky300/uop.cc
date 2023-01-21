package me.alpha432.oyvey.features.modules.combat;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.command.Command;

import javax.net.ssl.HttpsURLConnection;
import java.io.OutputStream;
import java.net.URL;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class CallBackup extends Module {
    
    public CallBackup() {
        super("CallBackup", "Pings for backup in media ping", Category.COMBAT, true, false, false);
    }
    
    public void onEnable() {
        
        String username = mc.player.getDisplayNameString();
        String icon = "https://minotar.net/avatar/" + username + "/128.png";
        long posX = Math.round(mc.player.posX / 10) * 10;
        long posZ = Math.round(mc.player.posZ / 10) * 10;
        String server = mc.getCurrentServerData().serverIP;
        
        String message = "{\"content\":\"<@&1052021548271284315> im on " + server + " at " + posX + " " + posZ + " get on\",\"username\":\"uop.cc - " + username + "\",\"avatar_url\":\"" + icon + "\"}";
        
        try {
            URL url = new URL("webhook here");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.addRequestProperty("Content-Type", "application/json");
            connection.addRequestProperty("User-Agent", "uop.cc");
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            OutputStream stream = connection.getOutputStream();
            stream.write(message.getBytes(StandardCharsets.UTF_8));
            stream.flush();
            stream.close();
            connection.getInputStream().close();
            connection.disconnect();
            Command.sendMessage("Message sent!");
        } catch (Exception e) {
            Command.sendMessage("Failed to send :(");
        }
        this.disable();
    }
} 
