package io.msterhuj.skydefender.commands.config;

import io.msterhuj.skydefender.commands.core.ICommand;
import io.msterhuj.skydefender.commands.core.annotations.Command;
import io.msterhuj.skydefender.commands.core.annotations.CommandArg;
import io.msterhuj.skydefender.commands.core.annotations.PlayerName;

@Command(name="config", aliases = {"c","conf"},description = "Configure the game settings")
public class ConfigCommand extends ICommand {

    @CommandArg(name="player", description = "target player name")
    @PlayerName
    public String player;

    @CommandArg(name="test", description = "test variable")
    public String test;



    public String error;
}
