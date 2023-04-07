package me.alpha432.oyvey.features.modules.misc;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.features.command.Command;
import me.alpha432.oyvey.util.Timer;

public class FutureColor extends Module {
    enum ColorMode {Lightness, Hue}
    Setting<ColorMode> colormode = register(new Setting("Mode", ColorMode.Lightness));
    Setting<Float> frequency = register(new Setting("Frequency", 1.0f, 0.1f, 3.0f));
    Setting<Integer> startHue = register(new Setting("StartHue", 0, 0, 360, v -> colormode.getValue() == ColorMode.Hue));
    Setting<Integer> endHue = register(new Setting("EndHue", 50, 0, 360, v -> colormode.getValue() == ColorMode.Hue));
    Timer commandDelay = new Timer();

    public FutureColor() {
        super("FutureColor", "automatically sets a future config", Category.MISC, true, false, false);
    }
    
    @Override
    public void onUpdate() {
        if (commandDelay.passedMs(50)) {
            if (colormode.getValue() == ColorMode.Lightness) {
                int lValue = (int) Math.floor(Math.sin(System.currentTimeMillis() * 0.002f * Math.PI * frequency.getValue()) * 25 + 50);
                mc.player.sendChatMessage(AutoConfig.getINSTANCE().prefix.getPlannedValue() + "colors lightness " + String.valueOf(lValue));
            } else {
                int trueEnd = endHue.getValue() + (endHue.getValue() < startHue.getValue() ? 360 : 0);
                int hValue = (int) Math.floor(Math.sin(System.currentTimeMillis() * 0.002f * Math.PI * frequency.getValue()) * ((trueEnd - startHue.getValue()) / 2) + ((trueEnd + startHue.getValue()) / 2)) % 360;
                mc.player.sendChatMessage(AutoConfig.getINSTANCE().prefix.getPlannedValue() + "colors hue " + String.valueOf(hValue));
            }
        }
    }
} 
