package me.alpha432.oyvey.features.modules.hud;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.event.events.Render2DEvent;
import me.alpha432.oyvey.util.ColorUtil;
import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.features.modules.client.ClickGui;

public class Watermark extends Module {

    public Setting<Boolean> versionNumber = register(new Setting("Version", false));
    public Setting<Integer> posX = register(new Setting("X", 2, 0, 1000));
    public Setting<Integer> posY = register(new Setting("Y", 2, 0, 500));

    public Watermark() {
        super("Watermark", "says hi to you :D", Category.HUD, true, false, false);
    }

    public void onRender2D(Render2DEvent event) {
        String string = ClickGui.getInstance().command.getPlannedValue();
        if ((ClickGui.getInstance()).rainbow.getValue().booleanValue()) {
            if ((ClickGui.getInstance()).rainbowModeHud.getValue() == ClickGui.rainbowMode.Static) {
                this.renderer.drawString(string, posX.getValue(), this.posY.getValue().intValue(), ColorUtil.rainbow((ClickGui.getInstance()).rainbowHue.getValue().intValue()).getRGB(), true);
            } else {
                int[] arrayOfInt = {1};
                char[] stringToCharArray = string.toCharArray();
                float f = 0.0F;
                for (char c : stringToCharArray) {
                    this.renderer.drawString(String.valueOf(c), posX.getValue() + f, this.posY.getValue().intValue(), ColorUtil.rainbow(arrayOfInt[0] * (ClickGui.getInstance()).rainbowHue.getValue().intValue()).getRGB(), true);
                    f += this.renderer.getStringWidth(String.valueOf(c));
                    arrayOfInt[0] = arrayOfInt[0] + 1;
                }
            }
        } else {
            this.renderer.drawString(string, posX.getValue(), this.posY.getValue().intValue(), ColorUtil.toRGBA((ClickGui.getInstance()).red.getValue(), (ClickGui.getInstance()).green.getValue(), (ClickGui.getInstance()).blue.getValue()), true);
        }
         
        if (this.versionNumber.getValue().booleanValue()) {
            this.renderer.drawString(" " + ClickGui.getInstance().version, posX.getValue() + this.renderer.getStringWidth(string), this.posY.getValue().intValue(), ColorUtil.toRGBA(255, 255, 255), true);
        }
    }
} 
