package jp.nagua.npractice.commands;

import jp.nagua.npractice.Main;
import jp.nagua.npractice.elements.serializes.SerializedItem;
import jp.nagua.npractice.utils.handlers.DataHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TriggerCommand implements CommandExecutor {

    private static final ArrayList<String> ARGS = new ArrayList<>(Arrays.asList(
            "OpenUnRankedQueue",
            "OpenRankedQueue",
            "LeaveQueue"
    ));

    static {
        Main.getPlugin().getCommand("trigger").setExecutor(new TriggerCommand());
        Main.getPlugin().getCommand("trigger").setTabCompleter(new TabCompleter() {
            @Override
            public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
                if(strings.length == 1) {
                    return ARGS;
                }
                return List.of();
            }
        });
        for(String string : ARGS) {
            if(DataHandler.getCommonDataFromDefault("Trigger-" + string) == null) {
                DataHandler.putCommonDataToDefault("Trigger-" + string, new SerializedItem(new ItemStack(Material.AIR)));
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length == 0) {
            commandSender.sendMessage(ChatColor.RED + "/trigger [Text]");
            return true;
        } else {
            String arg1 = strings[0];
            if(ARGS.contains(arg1)) {
                DataHandler.putCommonDataToDefault("Trigger-" + arg1, new SerializedItem(((Player) commandSender).getItemInHand()));
                commandSender.sendMessage(ChatColor.GREEN + "Set " + arg1 + " trigger item");
                return true;
            } else {
                commandSender.sendMessage(ChatColor.RED + "This trigger does not exist");
                return true;
            }
        }
    }
}
