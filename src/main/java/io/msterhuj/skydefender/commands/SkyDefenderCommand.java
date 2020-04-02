package io.msterhuj.skydefender.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SkyDefenderCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // skydefender *
        if (args.length >= 1) {
            if (args[0].equalsIgnoreCase("help")) {
                sender.sendMessage("help");
            } else if (args[0].equalsIgnoreCase("info")) {
                sender.sendMessage("info");
            } else if (args[0].equalsIgnoreCase("start")) {
                sender.sendMessage("starting game");
            } else if (args[0].equalsIgnoreCase("pause")) {
                sender.sendMessage("pausing game");
            } else if (args[0].equalsIgnoreCase("stop")) {
                sender.sendMessage("stopping game");
            } else if (args[0].equalsIgnoreCase("config")) {
                sender.sendMessage("config help");
            } else {
                sender.sendMessage("Unknown commands run /skydefender help");
            }
        } else {
            sender.sendMessage("Unknown commands run /skydefender help");
        }

        return true;
    }
}
