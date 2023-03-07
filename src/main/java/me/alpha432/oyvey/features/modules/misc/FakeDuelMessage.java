package me.alpha432.oyvey.features.modules.misc;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.command.Command;
import me.alpha432.oyvey.features.setting.Setting;
import com.mojang.realmsclient.gui.ChatFormatting;

public class FakeDuelMessage extends Module {

    public Setting<String> LoserName = register(new Setting("LoserName", "Catuquei"));

    public FakeDuelMessage() {
        super("FakeDuelMsg", "Prints a fake duel message", Category.MISC, true, false, false);
    }

    @Override
    public void onEnable() {
        if (fullNullCheck()) return;
        Command.sendSilentMessage(ChatFormatting.BLUE + "[Duels] " + ChatFormatting.WHITE + mc.player.getDisplayNameString() + ChatFormatting.GREEN + " (100) (+0) " + ChatFormatting.GRAY + "has defeated " + ChatFormatting.WHITE + this.LoserName.getPlannedValue() + " " + ChatFormatting.RED + "(100) (-0) " + ChatFormatting.GRAY + "on kit " + ChatFormatting.DARK_AQUA + "none " + ChatFormatting.GRAY + "with " + ChatFormatting.LIGHT_PURPLE + "10.0\u2665");
        this.disable();
    }
} 
