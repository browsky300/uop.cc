package me.alpha432.oyvey.features.modules.player;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import net.minecraft.client.settings.GameSettings;

public class LeftHand extends Module {
    public LeftHand() {
        super("LeftHand", "im too lazy to go into settings", Module.Category.PLAYER, false, false, false);
    }
    
    @Override
    public void onEnable() {
        mc.gameSettings.setOptionValue(GameSettings.Options.MAIN_HAND, 1);
    }
    
    @Override
    public void onDisable() {
        mc.gameSettings.setOptionValue(GameSettings.Options.MAIN_HAND, 0);
    }
}

