package io.msterhuj.skydefender.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SkyDefenderTabCompletion implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list = new ArrayList<>();

        // skydefender *
        if (args.length == 1) {
            list.add("help");
            list.add("start");
            list.add("pause");
            list.add("info");
            list.add("config");
            return list.stream().filter(s -> s.startsWith(args[0])).collect(Collectors.toList());
        }

        // skydefender config *
        if (args.length == 2 && args[0].equalsIgnoreCase("config")) {
            list.add("show");
            list.add("set");
            list.add("team");
            return list.stream().filter(s -> s.startsWith(args[1])).collect(Collectors.toList());
        }

        // skydefender config set *
        if (args.length == 3 && args[1].equalsIgnoreCase("set")) {
            list.add("spawn");
            list.add("banner");
            list.add("teleporter");
            return list.stream().filter(s -> s.startsWith(args[2])).collect(Collectors.toList());
        }

        // skydefender config set teleporter *
        if (args.length == 4 && args[2].equalsIgnoreCase("teleporter")) {
            list.add("island");
            list.add("ground");
            return list.stream().filter(s -> s.startsWith(args[3])).collect(Collectors.toList());
        }

        // skydefender config set teleporter island/ground *
        if (args.length == 5 && args[3] != null) {
            if (args[3].equalsIgnoreCase("island") || args[3].equalsIgnoreCase("ground")) {
                list.add("input");
                list.add("output");
                return list.stream().filter(s -> s.startsWith(args[4])).collect(Collectors.toList());
            }
        }

        // skydefender team

        return list;
    }
}
