package me.alpha432.oyvey.features.command.commands;

import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.features.command.Command;

public class OldfagSeedCommand
        extends Command {
    public OldfagSeedCommand() {
        super("oldfagseed", new String[]{"<name>"});
    }

    @Override
    public void execute(String[] commands) {
        OldfagSeedCommand.sendMessage("-1985258164");
    }
}

