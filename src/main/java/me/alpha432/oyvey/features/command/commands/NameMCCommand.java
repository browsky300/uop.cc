package me.alpha432.oyvey.features.command.commands;

import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.features.command.Command;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class NameMCCommand
        extends Command {
    public NameMCCommand() {
        super("namemc", new String[]{"<name>"});
    }

    @Override
    public void execute(String[] commands) {
        if (commands.length == 1) {
            NameMCCommand.sendMessage("Please specify a name.");
            return;
        }
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI("https://namemc.com/" + commands[0]));
                NameMCCommand.sendMessage("Opened browser.");
            } catch (Exception e) {
                e.printStackTrace();
                NameMCCommand.sendMessage("Error occured.");
            }
        }
    }
}

