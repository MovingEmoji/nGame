package jp.nagua.nGame.commands;

import jp.nagua.nGame.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RenameCommand implements CommandExecutor {

    static {
        Main.getPlugin().getCommand("rename").setExecutor(new RenameCommand());
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player) {
            if(strings.length == 0) {
                commandSender.sendMessage(ChatColor.RED + "Please type Text");
                return true;
            }
            String name = strings[0].replace("&", "§");
            ((Player) commandSender).getItemInHand().getItemMeta().setDisplayName(name);
            commandSender.sendMessage(ChatColor.GREEN + "Change ItemName to " + name);
            return true;
        }
        return false;
    }
}
