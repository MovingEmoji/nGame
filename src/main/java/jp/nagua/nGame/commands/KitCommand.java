package jp.nagua.nGame.commands;

import jp.nagua.nGame.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

public class KitCommand implements CommandExecutor {

    private static ArrayList<String> ARGS = new ArrayList<String>(Arrays.asList(
            "create",
            "delete",
            "list",
            "help",
            "setinventory",
            "seticon",
            "setflag",
            "setmaptype",
            "setround"
    ));

    static {
        Main.getPlugin().getCommand("kit").setExecutor(new KitCommand());
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player) {
            if(strings.length == 0) {
                commandSender.sendMessage(ChatColor.RED + "Please type /kit help");
                return true;
            } else {
                String arg1 = strings[0];
                if(ARGS.contains(arg1)) {
                    if(strings.length >= 2) {
                        String arg2 = strings[1];
                        switch (arg1) {
                            case "create":
                                createDefaultKit(arg2, ((Player) commandSender).getItemInHand());
                                return true;
                            default:
                                return true;
                        }
                    } else {
                        commandSender.sendMessage(ChatColor.RED + "Please type /kit help");
                        return true;
                    }
                } else {
                    commandSender.sendMessage(ChatColor.RED + "Please type /kit help");
                    return true;
                }
            }

        }
        return false;
    }

    private static void createDefaultKit(String name, ItemStack itemStack) {

    }
}
