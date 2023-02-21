package me.alpha432.oyvey.features.modules.combat;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.command.Command;
import me.alpha432.oyvey.features.setting.Setting;
import com.mojang.realmsclient.gui.ChatFormatting;

public class CAMessage extends Module {
    
    enum ClientName {Future, Gamesense, Impact, Kamiblue, Konas, Lambda, Newbase, Opfern, Oyvey, Phobos, Pyro, Rusherhack, Snow, Viknet, Wurstplus2}
    Setting<ClientName> clientname = register(new Setting("Client", ClientName.Future));
    
    public CAMessage() {
        super("CAMessage", "shows notifications for different CAs", Category.COMBAT, true, false, false);
    }

    @Override
    public void onEnable() {
        String onMessage = "";
        switch (clientname.getValue()) {
            case Future: {
                onMessage = ChatFormatting.RED + "[Future] " + ChatFormatting.GRAY + "AutoCrystal toggled " + ChatFormatting.GREEN + "on" + ChatFormatting.GRAY + ".";
                break;
            }
            case Gamesense: {
                onMessage = ChatFormatting.GRAY + "[" + ChatFormatting.WHITE + "Game" + ChatFormatting.GREEN + "Sense" + ChatFormatting.GRAY + "] " + ChatFormatting.GREEN + "AutoCrystal turned ON!";
                break;
            }
            case Impact: {
                onMessage = ChatFormatting.DARK_GRAY + "[" + ChatFormatting.BLUE + "Impact" + ChatFormatting.DARK_GRAY + "] " + ChatFormatting.GRAY + "Toggled " + ChatFormatting.BLUE + "Crystal Aura " + ChatFormatting.GRAY + "[" + ChatFormatting.GREEN + "ON" + ChatFormatting.GRAY + "]";
                break;
            }
            case Kamiblue: {
                onMessage = ChatFormatting.GRAY + "[" + ChatFormatting.BLUE + "\u30ab\u30df\u30d6\u30eb" + ChatFormatting.GRAY + "] " + ChatFormatting.WHITE + "CrystalAura " + ChatFormatting.GREEN + "enabled";
                break;
            }
            case Konas: {
                onMessage = ChatFormatting.DARK_GRAY + "[" + ChatFormatting.LIGHT_PURPLE + "Konas" + ChatFormatting.DARK_GRAY + "] " + ChatFormatting.WHITE + "AutoCrystal has been enabled";
                break;
            }
            case Lambda: {
                onMessage = ChatFormatting.GRAY + "[" + ChatFormatting.BLUE + "\u03bb" + ChatFormatting.GRAY + "] " + ChatFormatting.WHITE + "CrystalAura " + ChatFormatting.GREEN + "enabled";
                break;
            }
            case Newbase: {
                onMessage = ChatFormatting.BOLD + "AutoCrystal " + ChatFormatting.RESET + ChatFormatting.GREEN + "enabled.";
                break;
            }
            case Opfern: {
                onMessage = ChatFormatting.GRAY + "[" + ChatFormatting.RED + "\u1d0f\u1d18\ua730\u1d07\u0280\u0274" + ChatFormatting.GRAY + "] " + ChatFormatting.RED + "AutoCrystal " + ChatFormatting.GREEN + "Enabled!";
                break;
            }
            case Oyvey: {
                onMessage = ChatFormatting.BLUE + "<OyVey> " + ChatFormatting.GREEN + "AutoCrystal toggled on.";
                break;
            }
            case Phobos: {
                onMessage = ChatFormatting.BLUE + "<Phobos.eu> " + ChatFormatting.GREEN + "AutoCrystal enabled.";
                break;
            }
            case Pyro: {
                onMessage = ChatFormatting.DARK_RED + "" + ChatFormatting.BOLD + "[" + ChatFormatting.RESET + "" + ChatFormatting.DARK_RED + "Pyro" + ChatFormatting.BOLD + "] " + ChatFormatting.RESET + "" + ChatFormatting.GREEN + "AutoCrystal has been enabled.";
                break;
            }
            case Rusherhack: {
                onMessage = ChatFormatting.WHITE + "[" + ChatFormatting.GREEN + "rusherhack" + ChatFormatting.WHITE + "] AutoCrystal has been enabled";
                break;
            }
            case Snow: {
                onMessage = ChatFormatting.BLUE + "[" + ChatFormatting.AQUA + "Snow" + ChatFormatting.BLUE + "] [" + ChatFormatting.DARK_AQUA + "SnowAura" + ChatFormatting.BLUE + "] " + ChatFormatting.GREEN + "enabled";
                break;
            }
            case Viknet: {
                onMessage = ChatFormatting.GRAY + "VikNet " + ChatFormatting.DARK_GRAY + "\u27ab " + ChatFormatting.WHITE + "VikNetAura " + ChatFormatting.DARK_GREEN + "ON";
                break;
            }
            case Wurstplus2: {
                onMessage = ChatFormatting.GOLD + "Wurst+ 2 " + ChatFormatting.GRAY + "> " + ChatFormatting.WHITE + "we " + ChatFormatting.GREEN + "gaming";
                break;
            }
        }
        Command.sendSilentMessage(onMessage);
    }
    
    @Override
    public void onDisable() {
        String offMessage = "";
        switch (clientname.getValue()) {
            case Future: {
                offMessage = ChatFormatting.RED + "[Future] " + ChatFormatting.GRAY + "AutoCrystal toggled " + ChatFormatting.RED + "off" + ChatFormatting.GRAY + ".";
                break;
            }
            case Gamesense: {
                offMessage = ChatFormatting.GRAY + "[" + ChatFormatting.WHITE + "Game" + ChatFormatting.GREEN + "Sense" + ChatFormatting.GRAY + "] " + ChatFormatting.RED + "AutoCrystal turned OFF!";
                break;
            }
            case Impact: {
                offMessage = ChatFormatting.DARK_GRAY + "[" + ChatFormatting.BLUE + "Impact" + ChatFormatting.DARK_GRAY + "] " + ChatFormatting.GRAY + "Toggled " + ChatFormatting.BLUE + "Crystal Aura " + ChatFormatting.GRAY + "[" + ChatFormatting.RED + "OFF" + ChatFormatting.GRAY + "]";
                break;
            }
            case Kamiblue: {
                offMessage = ChatFormatting.GRAY + "[" + ChatFormatting.BLUE + "\u30ab\u30df\u30d6\u30eb" + ChatFormatting.GRAY + "] " + ChatFormatting.WHITE + "CrystalAura " + ChatFormatting.RED + "disabled";
                break;
            }
            case Konas: {
                offMessage = ChatFormatting.DARK_GRAY + "[" + ChatFormatting.LIGHT_PURPLE + "Konas" + ChatFormatting.DARK_GRAY + "] " + ChatFormatting.WHITE + "AutoCrystal has been disabled";
                break;
            }
            case Lambda: {
                offMessage = ChatFormatting.GRAY + "[" + ChatFormatting.BLUE + "\u03bb" + ChatFormatting.GRAY + "] " + ChatFormatting.WHITE + "CrystalAura " + ChatFormatting.RED + "disabled";
                break;
            }
            case Newbase: {
                offMessage = ChatFormatting.BOLD + "AutoCrystal " + ChatFormatting.RESET + ChatFormatting.RED + "disabled.";
                break;
            }
            case Opfern: {
                offMessage = ChatFormatting.GRAY + "[" + ChatFormatting.RED + "\u1d0f\u1d18\ua730\u1d07\u0280\u0274" + ChatFormatting.GRAY + "] " + ChatFormatting.RED + "AutoCrystal Disabled!";
                break;
            }
            case Oyvey: {
                offMessage = ChatFormatting.BLUE + "<OyVey> " + ChatFormatting.RED + "AutoCrystal toggled off.";
                break;
            }
            case Phobos: {
                offMessage = ChatFormatting.BLUE + "<Phobos.eu> " + ChatFormatting.RED + "AutoCrystal disabled.";
                break;
            }
            case Pyro: {
                offMessage = ChatFormatting.DARK_RED + "" + ChatFormatting.BOLD + "[" + ChatFormatting.RESET + "" + ChatFormatting.DARK_RED + "Pyro" + ChatFormatting.BOLD + "] " + ChatFormatting.RESET + "" + ChatFormatting.RED + "AutoCrystal has been disabled.";
                break;
            }
            case Rusherhack: {
                offMessage = ChatFormatting.WHITE + "[" + ChatFormatting.GREEN + "rusherhack" + ChatFormatting.WHITE + "] AutoCrystal has been disabled";
                break;
            }
            case Snow: {
                offMessage = ChatFormatting.BLUE + "[" + ChatFormatting.AQUA + "Snow" + ChatFormatting.BLUE + "] [" + ChatFormatting.DARK_AQUA + "SnowAura" + ChatFormatting.BLUE + "] " + ChatFormatting.RED + "disabled";
                break;
            }
            case Viknet: {
                offMessage = ChatFormatting.GRAY + "VikNet " + ChatFormatting.DARK_GRAY + "\u27ab " +  ChatFormatting.WHITE + "VikNetAura " + ChatFormatting.DARK_RED + "OFF";
                break;
            }
            case Wurstplus2: {
                offMessage = ChatFormatting.GOLD + "Wurst+ 2 " + ChatFormatting.GRAY + "> " + ChatFormatting.WHITE + "we aint " + ChatFormatting.RED + "gaming " + ChatFormatting.WHITE + "no more";
                break;
            }
        }
        Command.sendSilentMessage(offMessage);
    }
} 
