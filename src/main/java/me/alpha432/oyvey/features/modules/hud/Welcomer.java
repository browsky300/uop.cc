package me.alpha432.oyvey.features.modules.hud;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.event.events.Render2DEvent;
import me.alpha432.oyvey.util.ColorUtil;
import me.alpha432.oyvey.util.MathUtil;
import me.alpha432.oyvey.features.modules.client.ClickGui;
import me.alpha432.oyvey.features.modules.hud.Watermark;

public class Welcomer extends Module {

    public Setting<String> welcomerSetting = register(new Setting("Welcomer", "hi $n"));
    public Setting<Boolean> center = register(new Setting("Center", true));
    public Setting<Integer> posX = register(new Setting("X", 2, 0, 1000, v -> !center.getValue()));
    public Setting<Integer> posY = register(new Setting("Y", 2, 0, 500));

    public Welcomer() {
        super("Welcomer", "says hi to you :D", Category.HUD, true, false, false);
    }

    public void onRender2D(Render2DEvent event) {
        String wStr = welcomerSetting.getValue();
        wStr = wStr.replace("$c", ClickGui.getInstance().command.getValue());
        wStr = wStr.replace("$n", mc.player.getDisplayNameString());
        wStr = wStr.replace("$s", mc.getCurrentServerData() != null ? mc.getCurrentServerData().serverIP : "singleplayer");
        wStr = wStr.replace("$t", MathUtil.getShortTimeOfDay());
        wStr = wStr.replace("$v", ClickGui.getInstance().version);
        
        int trueX = (int) (center.getValue() ? (this.renderer.scaledWidth - this.renderer.getStringWidth(wStr)) / 2 : posX.getValue());
        
        if ((ClickGui.getInstance()).rainbow.getValue().booleanValue()) {
            if ((ClickGui.getInstance()).rainbowModeHud.getValue() == ClickGui.rainbowMode.Static) {
                this.renderer.drawString(wStr, trueX, posY.getValue(), ColorUtil.rainbow((ClickGui.getInstance()).rainbowHue.getValue()).getRGB(), true);
            } else {
                int[] arrayOfInt = {1};
                char[] stringToCharArray = wStr.toCharArray();
                float f = 0.0F;
                for (char c : stringToCharArray) {
                    this.renderer.drawString(String.valueOf(c), trueX + f, posY.getValue(), ColorUtil.rainbow(arrayOfInt[0] * (ClickGui.getInstance()).rainbowHue.getValue()).getRGB(), true);
                    f += this.renderer.getStringWidth(String.valueOf(c));
                    arrayOfInt[0] = arrayOfInt[0] + 1;
                }
            }
        } else {
            this.renderer.drawString(wStr, trueX, posY.getValue(), ColorUtil.toRGBA((ClickGui.getInstance()).red.getValue(), (ClickGui.getInstance()).green.getValue(), (ClickGui.getInstance()).blue.getValue()), true);
        }
    }
} 
