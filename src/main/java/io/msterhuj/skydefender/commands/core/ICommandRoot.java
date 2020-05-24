package io.msterhuj.skydefender.commands.core;

import lombok.Data;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public abstract class ICommandRoot extends ICommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String alias, String[] args) {
        if (args[args.length - 1].equalsIgnoreCase("help")) {
            return getCurrentCommand(args).help(commandSender);
        }
        return getCurrentCommand(args).execute(commandSender, command, alias, args);
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String alias, String[] args) {
        ICommand currentCommand = getCurrentCommand(args);

        List<String> suggestions = new ArrayList<>(currentCommand.getSubCommandsName());
        suggestions.addAll(currentCommand.getCurrentArgumentSuggestions(args));

        return suggestions.stream().filter(name -> name.startsWith(args[args.length - 1])).collect(Collectors.toList());
    }

}
