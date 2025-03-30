package jp.nagua.npractice.commands;

import jp.nagua.npractice.Main;
import jp.nagua.npractice.elements.serializes.SerializedInventory;
import jp.nagua.npractice.utils.handlers.DataHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InventoryCommand implements CommandExecutor {

    static {
        Main.getPlugin().getCommand("inventory").setExecutor(new InventoryCommand());
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player) {
            if(strings.length == 0) {
                commandSender.sendMessage(ChatColor.RED + "Please type Function[load/save/delete]");
                return true;
            }
            if(strings.length >= 1) {
                switch (strings[0]) {
                    case "load":
                        SerializedInventory.loadInventory((Player) commandSender, (SerializedInventory) DataHandler.getCommonDataFromDefault("Inventory-" + strings[1]));
                        commandSender.sendMessage(ChatColor.GREEN + "Load inventory from " + strings[1]);
                        break;
                    case "save":
                        DataHandler.putCommonDataToDefault("Inventory-" + strings[1], new SerializedInventory(strings[1], ((Player) commandSender).getInventory()));
                        commandSender.sendMessage(ChatColor.GREEN + "Save inventory with " + strings[1]);
                        break;
                    case "delete":
                        DataHandler.deleteCommonDataFromDefault("Inventory-" + strings[1]);
                        commandSender.sendMessage(ChatColor.RED + "Delete " + strings[1] + " inventory ");
                        break;
                    default:
                        commandSender.sendMessage(ChatColor.RED + "Please type correct Function[load/save/delete]");
                        break;
                }
                return true;
            }
        }
        return false;
    }
}
