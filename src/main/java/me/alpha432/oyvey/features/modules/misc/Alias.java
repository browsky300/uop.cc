package me.alpha432.oyvey.features.modules.misc;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;

public class Alias extends Module {
    private static Alias INSTANCE = new Alias();
    Setting<Integer> amount = register(new Setting("Shown", 3, 1, 10));
    public Setting<String> string1 = register(new Setting("String", ""));
    public Setting<String> alias1 = register(new Setting("Alias", ""));
    public Setting<String> string2 = register(new Setting("String", "", v -> amount.getValue() > 1));
    public Setting<String> alias2 = register(new Setting("Alias", "", v -> amount.getValue() > 1));
    public Setting<String> string3 = register(new Setting("String", "", v -> amount.getValue() > 2));
    public Setting<String> alias3 = register(new Setting("Alias", "", v -> amount.getValue() > 2));
    public Setting<String> string4 = register(new Setting("String", "", v -> amount.getValue() > 3));
    public Setting<String> alias4 = register(new Setting("Alias", "", v -> amount.getValue() > 3));
    public Setting<String> string5 = register(new Setting("String", "", v -> amount.getValue() > 4));
    public Setting<String> alias5 = register(new Setting("Alias", "", v -> amount.getValue() > 4));
    public Setting<String> string6 = register(new Setting("String", "", v -> amount.getValue() > 5));
    public Setting<String> alias6 = register(new Setting("Alias", "", v -> amount.getValue() > 5));
    public Setting<String> string7 = register(new Setting("String", "", v -> amount.getValue() > 6));
    public Setting<String> alias7 = register(new Setting("Alias", "", v -> amount.getValue() > 6));
    public Setting<String> string8 = register(new Setting("String", "", v -> amount.getValue() > 7));
    public Setting<String> alias8 = register(new Setting("Alias", "", v -> amount.getValue() > 7));
    public Setting<String> string9 = register(new Setting("String", "", v -> amount.getValue() > 8));
    public Setting<String> alias9 = register(new Setting("Alias", "", v -> amount.getValue() > 8));
    public Setting<String> string10 = register(new Setting("String", "", v -> amount.getValue() > 9));
    public Setting<String> alias10 = register(new Setting("Alias", "", v -> amount.getValue() > 9));
    
    public Alias() {
        super("Alias", "add string aliases", Module.Category.MISC, false, false, false);
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

    public String replaceWithAliases(String text) {
        if (isOn()) {
            /*Module module = OyVey.moduleManager.getModuleByName("Alias");
            for (int i = 1; i > 10; i++) {
                if (module.getSettingByName("string" + String.valueOf(i)).getValue() != "") {
                    text = text.replace((module.getSettingByName("string" + String.valueOf(i))).getValue(), (module.getSettingByName("alias" + String.valueOf(i))).getValue());
                }
            }*/
            if (false) System.out.println("the following code is very stupid do not do this");
            if (string1.getValue() != "") text = text.replace(string1.getValue(), alias1.getValue());
            if (string2.getValue() != "") text = text.replace(string2.getValue(), alias2.getValue());
            if (string3.getValue() != "") text = text.replace(string3.getValue(), alias3.getValue());
            if (string4.getValue() != "") text = text.replace(string4.getValue(), alias4.getValue());
            if (string5.getValue() != "") text = text.replace(string5.getValue(), alias5.getValue());
            if (string6.getValue() != "") text = text.replace(string6.getValue(), alias6.getValue());
            if (string7.getValue() != "") text = text.replace(string7.getValue(), alias7.getValue());
            if (string8.getValue() != "") text = text.replace(string8.getValue(), alias8.getValue());
            if (string9.getValue() != "") text = text.replace(string9.getValue(), alias9.getValue());
            if (string10.getValue() != "") text = text.replace(string10.getValue(), alias10.getValue());
        }
        return text;
    }
}

