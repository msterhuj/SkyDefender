package io.msterhuj.skydefender.commands;

import io.msterhuj.skydefender.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SkyDefenderTabCompletion implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list = new ArrayList<>();

        if (args.length == 1) {
            list.add("start");
            list.add("pause");
            list.add("stop");
            list.add("config");
            return list;
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("config")) {
            list.add("show");
            list.add("set");
            list.add("team");
            return list;
        }

        if (args.length == 3 && args[1].equalsIgnoreCase("set")) {
            list.add("spawn");
            list.add("banner");
            list.add("teleporter");
            list.add("");
            list.add("");
            list.add("");
        }
        return list;
    }
}
