package jp.nagua.nGame.commands;

import jp.nagua.nGame.Main;
import jp.nagua.nGame.elements.SerializedInventory;
import jp.nagua.nGame.utils.DataHandler;
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
                        SerializedInventory.loadInventory((Player) commandSender, (SerializedInventory) DataHandler.getCommonDataFromDefault(strings[1] + "Inventory"));
                        commandSender.sendMessage(ChatColor.GREEN + "Load inventory from " + strings[1]);
                        break;
                    case "save":
                        DataHandler.putCommonDataToDefault(strings[1] + "Inventory", new SerializedInventory(strings[1], ((Player) commandSender).getInventory()));
                        commandSender.sendMessage(ChatColor.GREEN + "Save inventory with " + strings[1]);
                        break;
                    case "delete":
                        DataHandler.deleteCommonDataFromDefault(strings[1] + "Inventory");
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
