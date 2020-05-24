package io.msterhuj.skydefender.commands.core;

import io.msterhuj.skydefender.commands.core.annotations.Command;
import lombok.Data;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;
import java.util.stream.Collectors;

@Data
public abstract class ICommandRoot extends ICommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String alias, String[] args) {
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String alias, String[] args) {
        return getCurrentCommand(args).getSubCommandsName().stream().filter(name -> name.startsWith(args[args.length -1])).collect(Collectors.toList());
    }

    public String getName() {
        return getClass().getAnnotation(Command.class).name();
    }


}
