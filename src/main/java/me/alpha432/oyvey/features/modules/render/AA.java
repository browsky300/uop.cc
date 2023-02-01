package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.util.Timer;

public class AA extends Module {
    private static AA INSTANCE = new AA();
    
    public final Setting<Boolean> standing = this.register(new Setting<Boolean>("Standing", true));
    public final Setting<Boolean> sneaking = this.register(new Setting<Boolean>("Sneaking", true));
    public final Setting<Boolean> lookback = this.register(new Setting<Boolean>("Lookback", false));
    public final Setting<Boolean> spin = this.register(new Setting<Boolean>("Spin", false));
    public final Setting<Integer> update = this.register(new Setting<Integer>("Update", 0, 0, 500, v -> spin.getValue()));
    public final Timer updateDelay = new Timer();
    public float offset = 0;
    public float finalOffset = 0;
    
    public AA() {
        super("AA", "smashmod", Module.Category.RENDER, false, false, false);
        this.setInstance();
    }
    
    @Override
    public void onUpdate() {
        if (spin.getValue()) {
            if (updateDelay.passedMs(update.getValue())) {
                offset = (float) Math.random() * 360;
                updateDelay.reset();
            }
        } else {
            offset = 0;
        }
        finalOffset = (lookback.getValue() && System.currentTimeMillis() % 100 > 50 ? mc.getRenderManager().playerViewY - mc.player.rotationYaw + 180 : offset);
    }

    public static AA getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new AA();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

