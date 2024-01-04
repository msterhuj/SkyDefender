package io.msterhuj.skydefender.commands.core;

import lombok.Data;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

@Data
public abstract class ICommandRoot extends ICommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String alias, String[] args) {
        if (args.length > 0 && args[args.length - 1].equalsIgnoreCase("help")) {
            return getCurrentCommand(args).help(commandSender);
        }
        return getCurrentCommand(args).execute(commandSender, command, alias, args);
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String alias, String[] args) {
        return getSuggestions(args);
    }

}
