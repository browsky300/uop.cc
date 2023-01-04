package me.alpha432.oyvey.features.modules.misc;

import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public class XormiosModule extends Module {

    public XormiosModule() {
        super("XormiosModule", "why doesnt a module not give you strength - xormios", Category.MISC, true, false, false);
    }

    @Override
    public void onUpdate() {
        mc.player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 1201));
    }
    
    @Override
    public void onDisable() {
        mc.player.removePotionEffect(MobEffects.STRENGTH);
    }
} 
