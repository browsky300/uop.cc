package me.alpha432.oyvey.features.modules.misc;

import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public class GoodLuckCharm extends Module {

    public GoodLuckCharm() {
        super("GoodLuckCharm", "helps you win", Category.MISC, true, false, false);
    }

    @Override
    public void onUpdate() {
        mc.player.addPotionEffect(new PotionEffect(MobEffects.LUCK, 5700));
    }
    
    @Override
    public void onDisable() {
        mc.player.removePotionEffect(MobEffects.LUCK);
    }
} 
