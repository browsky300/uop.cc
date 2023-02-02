package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.init.SoundEvents;

import java.util.ArrayList;
import java.util.List;

public class DeathEffects extends Module {
    private static DeathEffects INSTANCE = new DeathEffects();
    public enum DeathMode {Glitchy, Heaven, Default};
    public Setting<DeathMode> deathmode = register(new Setting("Animation", DeathMode.Glitchy));
    public Setting<Boolean> lightning = register(new Setting("Lightning", false));
    List<EntityPlayer> alreadyDead = new ArrayList();
    
    public DeathEffects() {
        super("DeathEffects", "Makes the death animation awesome", Module.Category.RENDER, false, false, false);
        this.setInstance();
    }
    
    @Override
    public void onUpdate() {
        if (mc.world == null) {
            alreadyDead.clear();
            return;
        }
        if (lightning.getValue()) {
            for (EntityPlayer player : mc.world.playerEntities) {
                if (player.getHealth() > 0) {
                    if (alreadyDead.contains(player)) alreadyDead.remove(player);
                    continue;
                }
                if (alreadyDead.contains(player)) continue;
                
                mc.world.spawnEntity(new EntityLightningBolt(mc.world, player.posX, player.posY, player.posZ, true));
                mc.player.playSound(SoundEvents.ENTITY_LIGHTNING_THUNDER, 0.5f, 1.0f);
            }
        }
    }
       
    public static DeathEffects getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new DeathEffects();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

