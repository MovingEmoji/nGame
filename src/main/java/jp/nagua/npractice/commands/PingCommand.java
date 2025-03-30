package jp.nagua.npractice.commands;

import jp.nagua.npractice.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PingCommand implements CommandExecutor {

    static {
        Main.getPlugin().getCommand("ping").setExecutor(new PingCommand());
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player) {
            if(strings.length == 0) {
                CraftPlayer craftplayer = (CraftPlayer) commandSender;
                commandSender.sendMessage(ChatColor.AQUA + "Your average ping is " + craftplayer.getHandle().ping + "ms");
            } else {
                if(Bukkit.getServer().getOnlinePlayers().contains(Bukkit.getPlayer(strings[0]))) {
                    CraftPlayer craftplayer = (CraftPlayer) Bukkit.getPlayer(strings[0]);
                    commandSender.sendMessage(ChatColor.AQUA + craftplayer.getName() +"'s average ping is " + craftplayer.getHandle().ping + "ms");
                } else {
                    commandSender.sendMessage(ChatColor.RED + "That player is offline");
                }
            }
            return true;
        }
        System.out.println("other sender " + commandSender.toString());
        return false;
    }
}
