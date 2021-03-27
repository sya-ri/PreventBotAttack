package com.github.syari.bungee.PreventBotAttack;

import net.md_5.bungee.api.*;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.plugin.*;
import java.util.*;

public class BlackListCommand extends Command {
    public BlackListCommand() {
        super("blacklist", "blacklist.command");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        int length = args.length;
        if (length < 1) {
            sendHelp(sender);
        } else {
            BlackList blackList = Main.getBlackList();
            switch (args[0].toLowerCase()) {
                case "add": {
                    if (length < 2) {
                        sender.sendMessage(new ComponentBuilder("Enter the IP, such as 0.0.0.0").color(ChatColor.RED).create());
                    } else {
                        String ip = args[1];
                        if (blackList.contains(ip)) {
                            sender.sendMessage(new ComponentBuilder("Already added to blacklist: " + ip).color(ChatColor.RED).create());
                        } else {
                            blackList.add(ip);
                            sender.sendMessage(new ComponentBuilder("Add to blackList: " + ip).color(ChatColor.GREEN).create());
                        }
                    }
                    break;
                }
                case "remove": {
                    if (length < 2) {
                        sender.sendMessage(new ComponentBuilder("Enter the IP, such as 0.0.0.0").color(ChatColor.RED).create());
                    } else {
                        String ip = args[1];
                        if (blackList.contains(ip)) {
                            blackList.remove(ip);
                            sender.sendMessage(new ComponentBuilder("Remove from blacklist: " + ip).color(ChatColor.GREEN).create());
                        } else {
                            sender.sendMessage(new ComponentBuilder("Does not exist in blacklist: " + ip).color(ChatColor.RED).create());
                        }
                    }
                    break;
                }
                case "list": {
                    StringBuilder builder = new StringBuilder();
                    List<String> list = blackList.getList();
                    builder.append("IP Blacklist:");
                    for (String ip : list) {
                        builder.append("\n").append("- ").append(ip);
                    }
                    sender.sendMessage(new ComponentBuilder(builder.toString()).color(ChatColor.AQUA).create());
                    break;
                }
                default: {
                    sendHelp(sender);
                }
            }
        }
    }

    private void sendHelp(CommandSender sender) {
        String message = "Command List: /blacklist\n" +
                "/blacklist add <IP> : Add IP to blacklist\n" +
                "/blacklist remove <IP> : Remove IP from blacklist\n" +
                "/blacklist list : Show blacklist\n" +
                "/blacklist help : Show command help";
        sender.sendMessage(new ComponentBuilder(message).color(ChatColor.AQUA).create());
    }
}
