package me.alpha432.oyvey.features.modules.hud;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.features.modules.client.ClickGui;
import me.alpha432.oyvey.event.events.Render2DEvent;
import me.alpha432.oyvey.util.ColorUtil;
import me.alpha432.oyvey.OyVey;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import java.util.ArrayList;
import java.util.List;

public class BetterPotions extends Module {

    enum ListPos {TopRight, Side, Hotbar}
    Setting<ListPos> listpos = register(new Setting("Position", ListPos.TopRight));
    enum importantPotionMode {All, Important, Oldfag}
    Setting<importantPotionMode> importantpotionmode = register(new Setting("Show", importantPotionMode.All));
    Setting<Boolean> sync = register(new Setting("Sync", false));
    
    public BetterPotions() {
        super("BetterPotions", "Potions list but better", Category.HUD, true, false, false);
    }

    public void onRender2D(Render2DEvent event) {
        if (fullNullCheck()) return;
        List<PotionEffect> effects = new ArrayList<>(mc.player.getActivePotionEffects());
        
        int i = 0;
        for (PotionEffect potionEffect : effects) {
            int posX = 0;
            int posY = 0;
            
            switch (listpos.getValue()) {
                case TopRight: {
                    String str = OyVey.potionManager.getColoredPotionString(potionEffect);
                    posX = this.renderer.scaledWidth - this.renderer.getStringWidth(str) - 2;
                    posY = 2 + i;
                    break;
                }
                case Side: {
                    posX = 2;
                    posY = 236 + i;
                    break;
                }
                case Hotbar: {
                    String str = OyVey.potionManager.getColoredPotionString(potionEffect);
                    posX = (this.renderer.scaledWidth - this.renderer.getStringWidth(str)) / 2;
                    posY = this.renderer.scaledHeight - 85 - i;
                    break;
                }
            }
            
            if (drawPotion(potionEffect, posX, posY)) {
                i += 9;
            }
        }
    }
    
    
    public boolean drawPotion(PotionEffect potionEffect, int posX, int posY) {
        String potionString = potionEffect.getPotion().getName();
        String str = OyVey.potionManager.getColoredPotionString(potionEffect);
        int color = (!sync.getValue() ? potionEffect.getPotion().getLiquidColor() : (ClickGui.getInstance().rainbow.getValue() ? ColorUtil.toRGBA(ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue())) : ColorUtil.toRGBA(ClickGui.getInstance().red.getValue(), ClickGui.getInstance().green.getValue(), ClickGui.getInstance().blue.getValue())));

        switch (importantpotionmode.getValue()) {
            case All: {
                this.renderer.drawString(str, posX, posY, color, true);
                return true;
            }
            case Important: {
                switch (potionString) {
                    case "effect.resistance": {
                        return false;
                    }
                    case "effect.fireResistance": {
                        return false;
                    }
                    case "effect.absorption": {
                        return false;
                    }
                    case "effect.regeneration": {
                        return false;
                    }
                    case "effect.wither": {
                        return false;
                    }
                    default : {
                        this.renderer.drawString(str, posX, posY, color, true);
                        return true;
                    }
                }
            }
            case Oldfag: {
                switch (potionString) {
                    case "effect.resistance": {
                        return false;
                    }
                    case "effect.fireResistance": {
                        return false;
                    }
                    case "effect.regeneration": {
                        return false;
                    }
                    default : {
                        this.renderer.drawString(str, posX, posY, color, true);
                        return true;
                    }
                }
            }
        }
    return false;
    }
} 
