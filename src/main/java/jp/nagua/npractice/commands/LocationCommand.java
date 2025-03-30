package jp.nagua.npractice.commands;

import jp.nagua.npractice.Main;
import jp.nagua.npractice.elements.FixedLocation;
import jp.nagua.npractice.utils.DataHandler;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LocationCommand implements CommandExecutor {

    static {
        Main.getPlugin().getCommand("location").setExecutor(new LocationCommand());
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player) {
            if(strings.length == 0) {
                commandSender.sendMessage(ChatColor.RED + "Please type LocaitonName");
                return true;
            }
            Location location = ((Player) commandSender).getLocation();
            FixedLocation flocation = new FixedLocation(location);
            DataHandler.putCommonDataToDefault(strings[0], flocation);
            commandSender.sendMessage(ChatColor.GREEN + "Save location at [x:" + (int) location.getX() + " y:" + (int) location.getY() + " z:" + (int) location.getZ() + "] to " + strings[0]);
            return true;
        }
        return false;
    }
}
