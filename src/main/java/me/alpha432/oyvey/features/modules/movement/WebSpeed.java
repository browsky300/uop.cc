package me.alpha432.oyvey.features.modules.movement;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;

public class WebSpeed extends Module {
    public Setting<Integer> multiplier = this.register(new Setting<Integer>("Multiplier", 1, 1, 20));

    public WebSpeed() {
        super("WebSpeed", "move around in webs", Category.MOVEMENT, true, false, false);
    }

    @Override
    public void onUpdate() {
        if (mc.player.isInWeb) mc.player.motionY *= multiplier.getValue();
    }
    
    @Override
    public String getDisplayInfo() {
        return String.valueOf(multiplier.getValue());
    }
}

