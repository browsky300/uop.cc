package me.alpha432.oyvey.features.modules.misc;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.features.command.Command;

public class AutoConfig extends Module {
    public Setting<String> prefix = register(new Setting("Prefix", ","));

    public AutoConfig() {
        super("AutoConfig", "automatically sets a future config", Category.MISC, true, false, false);
    }
    
    public void onEnable() {
        if (mc.getCurrentServerData() != null) {
            mc.player.sendChatMessage(prefix.getPlannedValue() + "config load " + mc.getCurrentServerData().serverIP);
        }
        this.disable();
    }
} 
