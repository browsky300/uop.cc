package me.alpha432.oyvey.features.modules.misc;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;

public class Alias extends Module {
    private static Alias INSTANCE = new Alias();
    enum AliasMode {Add, Delete}
    Setting<AliasMode> aliasmode = register(new Setting("Mode", AliasMode.Add));
    public Setting<String> addstring = register(new Setting("String", ""));
    public Setting<String> addalias = register(new Setting("Alias", ""));
    public Setting<String> delstring = register(new Setting("String", ""));
    
    public Alias() {
        super("Alias", "add string aliases", Module.Category.RENDER, false, false, false);
        this.setInstance();
    }

    public static Alias getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new Alias();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

