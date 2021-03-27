package com.github.syari.bungee.PreventBotAttack;

import net.md_5.bungee.api.*;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.plugin.*;

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
                            sender.sendMessage(new ComponentBuilder("Already added to the blacklist: " + ip).color(ChatColor.RED).create());
                        } else {
                            blackList.add(ip);
                            sender.sendMessage(new ComponentBuilder("Add to blackList: " + ip).color(ChatColor.GREEN).create());
                        }
                    }
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
                "/blacklist help : Show command help";
        sender.sendMessage(new ComponentBuilder(message).color(ChatColor.AQUA).create());
    }
}
