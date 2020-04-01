package io.msterhuj.skydefender.commands;

import io.msterhuj.skydefender.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class SkyDefenderTabCompletion implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list = new ArrayList<>();

        // skydefender *
        if (args.length == 1) {
            list.add("start");
            list.add("pause");
            list.add("stop");
            list.add("config");
            return list;
        }

        // skydefender config *
        if (args.length == 2 && args[0].equalsIgnoreCase("config")) {
            list.add("show");
            list.add("set");
            list.add("team");
            return list;
        }

        // skydefender config set *
        if (args.length == 3 && args[1].equalsIgnoreCase("set")) {
            list.add("spawn");
            list.add("banner");
            list.add("teleporter");
        }

        // skydefender config set teleporter *
        if (args.length == 4 && args[2].equalsIgnoreCase("teleporter")) {
            list.add("island");
            list.add("ground");
        }

        // skydefender team

        return list;
    }
}
