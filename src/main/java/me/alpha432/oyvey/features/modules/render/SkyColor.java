package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.features.modules.client.ClickGui;
import me.alpha432.oyvey.util.ColorUtil;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SkyColor extends Module {
    public Setting<Boolean> rainbow = register(new Setting("Rainbow", false));
    public Setting<Integer> red = register(new Setting("Red", 255, 0, 255, v -> !rainbow.getValue()));
    public Setting<Integer> green = register(new Setting("Green", 255, 0, 255, v -> !rainbow.getValue()));
    public Setting<Integer> blue = register(new Setting("Blue", 255, 0, 255, v -> !rainbow.getValue()));
    
    public SkyColor() {
        super("SkyColor", "changes the color of the sky", Category.RENDER, true, false, false);
    }

    @SubscribeEvent
    public void fogColors(EntityViewRenderEvent.FogColors event) {
        if (rainbow.getValue()) {
            event.setRed(ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRed() / 255f);
            event.setGreen(ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getGreen() / 255f);
            event.setBlue(ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getBlue() / 255f);
        } else {
            event.setRed(red.getValue() / 255f);
            event.setGreen(green.getValue() / 255f);
            event.setBlue(blue.getValue() / 255f);
        }
    }
} 
