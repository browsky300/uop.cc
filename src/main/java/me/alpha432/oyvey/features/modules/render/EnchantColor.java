package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;

public class EnchantColor extends Module {
    private static EnchantColor INSTANCE = new EnchantColor();
    public Setting<Integer> red = this.register(new Setting<Integer>("Red", 255, 0, 255));
    public Setting<Integer> green = this.register(new Setting<Integer>("Green", 0, 0, 255));
    public Setting<Integer> blue = this.register(new Setting<Integer>("Blue", 0, 0, 255));
    
    public EnchantColor() {
        super("EnchantColor", "Changes the enchantment glint colour.", Module.Category.RENDER, false, false, false);
        this.setInstance();
    }

    public static EnchantColor getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new EnchantColor();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

