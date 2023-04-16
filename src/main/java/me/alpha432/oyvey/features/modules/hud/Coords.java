package me.alpha432.oyvey.features.modules.hud;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.features.modules.client.ClickGui;
import me.alpha432.oyvey.event.events.Render2DEvent;
import me.alpha432.oyvey.util.ColorUtil;
import com.mojang.realmsclient.gui.ChatFormatting;

public class Coords extends Module {
    
    public Setting<Integer> decimals = register(new Setting("Decimals", 0, -1, 2));
    public Setting<Boolean> nether = register(new Setting("Nether", true));

    public Coords() {
        super("Coords", "shows coords", Category.HUD, true, false, false);
    }

    public void onRender2D(Render2DEvent event) {
        float netherMult = mc.world.getBiome(mc.player.getPosition()).getBiomeName().equals("Hell") ? 8 : 0.125f;
        int color = (ClickGui.getInstance().rainbow.getValue() ? ColorUtil.toRGBA(ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue())) : ColorUtil.toRGBA(ClickGui.getInstance().red.getValue(), ClickGui.getInstance().green.getValue(), ClickGui.getInstance().blue.getValue()));
        String str = "XYZ " + ChatFormatting.WHITE + formatCoord(mc.player.posX) + " " + formatCoord(mc.player.posY) + " " + formatCoord(mc.player.posZ) + ChatFormatting.RESET + " ";
        if (nether.getValue() && !mc.world.getBiome(mc.player.getPosition()).getBiomeName().equals("The End")) {
            str = str + "[" + ChatFormatting.WHITE + formatCoord(mc.player.posX * netherMult) + " " + formatCoord(mc.player.posZ * netherMult) + ChatFormatting.RESET + "]";
        }
        this.renderer.drawString(str, 2, this.renderer.scaledHeight - 9, color, true);
    }

    public String formatCoord(double n) {
        n = Math.round(n * Math.pow(10, decimals.getValue())) / Math.pow(10, decimals.getValue());
        switch (decimals.getValue()) { // this is dumb
            case -1: {
                return String.valueOf((int) n);
            }
            case 0: {
                return String.valueOf((int) n);
            }
            case 1: {
                return String.format("%.1f%n", n);
            }
            case 2: {
                return String.format("%.2f%n", n);
            }
            default: {
                return String.valueOf(n);
            }
        }
    }
}