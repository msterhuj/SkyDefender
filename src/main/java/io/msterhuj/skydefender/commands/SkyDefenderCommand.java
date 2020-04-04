package io.msterhuj.skydefender.commands;

import io.msterhuj.skydefender.SkyDefender;
import io.msterhuj.skydefender.core.teleporter.TeleporterType;
import io.msterhuj.skydefender.core.teleporter.TeleporterZone;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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

                // skydefender config *
                if (args.length >= 2) {
                    if (args[1].equalsIgnoreCase("set")) {

                        // skydefender config set *
                        if (args.length >= 3) {
                            if (args[2].equalsIgnoreCase("teleporter") && sender instanceof Player) {

                                Player player = (Player) sender;

                                // skydefender config set teleporter *
                                if (args.length >= 4) {
                                    if (args[3].equalsIgnoreCase("island")) {

                                        // skydefender config set teleporter island *
                                        if (args.length >= 5) {
                                            if (args[4].equalsIgnoreCase("input")) {
                                                SkyDefender.getInstance().getTeleporterManager().add(player, TeleporterZone.ISLAND, TeleporterType.INPUT);
                                            } else if (args[4].equalsIgnoreCase("output")) {
                                                SkyDefender.getInstance().getTeleporterManager().add(player, TeleporterZone.ISLAND, TeleporterType.OUTPUT);
                                            }
                                        }

                                    } else if (args[3].equalsIgnoreCase("ground")) {

                                        // skydefender config set teleporter ground *
                                        if (args.length >= 5) {
                                            if (args[4].equalsIgnoreCase("input")) {
                                                SkyDefender.getInstance().getTeleporterManager().add(player, TeleporterZone.GROUND, TeleporterType.INPUT);
                                            } else if (args[4].equalsIgnoreCase("output")) {
                                                SkyDefender.getInstance().getTeleporterManager().add(player, TeleporterZone.GROUND, TeleporterType.OUTPUT);
                                            }
                                        }

                                    }
                                }
                            }
                        }
                    }
                }

            } else {
                sender.sendMessage("Unknown commands run /skydefender help");
            }
        } else {
            sender.sendMessage("Unknown commands run /skydefender help");
        }

        return true;
    }
}
