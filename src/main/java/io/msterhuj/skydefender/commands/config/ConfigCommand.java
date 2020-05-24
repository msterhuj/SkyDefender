package io.msterhuj.skydefender.commands.config;

import io.msterhuj.skydefender.commands.core.ICommand;
import io.msterhuj.skydefender.commands.core.annotations.Command;
import io.msterhuj.skydefender.commands.core.annotations.CommandArg;

@Command(name="config", aliases = {"c","conf"},description = "Configure the game settings")
public class ConfigCommand extends ICommand {

    @CommandArg(name="test", description = "test variable", index="2")
    @PlayerName
    public String test;

    public String error;
}
