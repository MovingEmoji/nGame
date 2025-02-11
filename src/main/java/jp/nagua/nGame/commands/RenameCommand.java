package jp.nagua.nGame.commands;

import jp.nagua.nGame.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
            StringBuilder stringBuilder = new StringBuilder();
            for(int i = 0; i < strings.length; i++) {

                stringBuilder.append(strings[i].replace("&",));
                if(i != strings.length - 1) {
                    stringBuilder.append(" ");
                }
            }
            ItemStack item = ((Player) commandSender).getItemInHand();
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(stringBuilder.toString());
            item.setItemMeta(meta);
            ((Player) commandSender).setItemInHand(item);
            commandSender.sendMessage(ChatColor.GREEN + "Change ItemName to " + stringBuilder.toString());
            return true;
        }
        return false;
    }
}
