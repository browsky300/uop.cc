package me.alpha432.oyvey.features.modules.hud;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.event.events.Render2DEvent;
import me.alpha432.oyvey.util.RenderUtil;
import me.alpha432.oyvey.util.ColorUtil;
import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.features.modules.client.ClickGui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import com.mojang.realmsclient.gui.ChatFormatting;

import java.util.ArrayList;
import java.util.List;

public class ArrayListJ extends Module {
    public enum RenderMode {Left, Down, Up}
    public Setting<RenderMode> rendermode = register(new Setting("Render", RenderMode.Left));
    public enum ColorMode {Category, Sync, Future}
    public Setting<ColorMode> colormode = register(new Setting("Color", ColorMode.Category));
    public Setting<Boolean> prefix = register(new Setting("Prefix", false));

    public ArrayListJ() {
        super("ArrayList", "renders your current modules", Category.HUD, true, false, false);
    }

    public void onRender2D(Render2DEvent event) {
        OyVey.moduleManager.sortModules(true);
        for (int k = 0; k < OyVey.moduleManager.sortedModules.size(); k++) {
            Module module = OyVey.moduleManager.sortedModules.get(k);
            String str = module.getFullArrayString() + ChatFormatting.RESET;
            if (prefix.getValue()) {
                if (rendermode.getValue() == RenderMode.Left) {
                    str = ">" + str;
                } else {
                    str = str + "<";
                }
            }
            int x = (rendermode.getValue() == RenderMode.Left ? 2 : this.renderer.scaledWidth - 2 - this.renderer.getStringWidth(str));
            int y = (rendermode.getValue() == RenderMode.Left ? 11 : (rendermode.getValue() == RenderMode.Up ? 2 : this.renderer.scaledHeight - 10)) + (k * 9 * (rendermode.getValue() == RenderMode.Down ? -1 : 1));
            int color = 0;
            switch (colormode.getValue()) {
                case Category: {
                    color = getCategoryColor(module.getCategory());
                    break;
                }
                case Sync: { // dear lord
                    color = (ClickGui.getInstance()).rainbow.getValue().booleanValue() ? (((ClickGui.getInstance()).rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(k * (ClickGui.getInstance()).rainbowHue.getValue().intValue()).getRGB() : ColorUtil.rainbow((ClickGui.getInstance()).rainbowHue.getValue().intValue()).getRGB()) : ColorUtil.toRGBA((ClickGui.getInstance()).red.getValue(), (ClickGui.getInstance()).green.getValue(), (ClickGui.getInstance()).blue.getValue());
                    break;
                }
                case Future: {
                    color = 0x5bcefa;
                    if (k % 2 != 0) color = 0xf5a9b8;
                    if ((k + 2) % 4 == 0) color = 0xffffff;
                    break;
                }
            }

            this.renderer.drawString(str, x, y, color, true);
        }
    }
    
    public static int getCategoryColor(Category c) {
        switch (c) {
            case COMBAT: {
                return 0xff4040;
            }
            case MISC: {
                return 0x00ff00;
            }
            case RENDER: {
                return 0xff80ff;
            }
            case MOVEMENT: {
                return 0x0040ff;
            }
            case PLAYER: {
                return 0x80c0ff;
            }
            case CLIENT: {
                return 0x408080;
            }
            case HUD: {
                return 0x408040;
            }
        }
        return 0xffffff;
    }
}