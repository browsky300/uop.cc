package me.alpha432.oyvey.features.modules.player;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;

public class NoTrace extends Module {
    private static NoTrace INSTANCE = new NoTrace();
    public enum NoTraceMode {Pickaxe, Gap, Notcrystal, All}
    public Setting<NoTraceMode> notracemode = register(new Setting("Mode", NoTraceMode.Notcrystal));

    public NoTrace() {
        super("NoTrace", "cant come up with a creative descrption its 11:54 pm", Category.PLAYER, false, false, false);
        this.setInstance();
    }

    public static NoTrace getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new NoTrace();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
    
    @Override
    public String getDisplayInfo() {
        return notracemode.currentEnumName();
    }
}

