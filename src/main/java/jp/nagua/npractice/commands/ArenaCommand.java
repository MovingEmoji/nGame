package jp.nagua.npractice.commands;

import jp.nagua.npractice.Main;
import jp.nagua.npractice.elements.Arena;
import jp.nagua.npractice.elements.FixedLocation;
import jp.nagua.npractice.types.MapType;
import jp.nagua.npractice.utils.managers.ArenaManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArenaCommand implements CommandExecutor {

    private static String ERR_HELP = "Please type /arena help";

    private static final ArrayList<String> ARGS = new ArrayList<>(Arrays.asList(
            "create",
            "delete",
            "list",
            "help",
            "setmaptype",
            "setspawn1",
            "setspawn2",
            "teleport",
            "load",
            "unload"
    ));

    static {
        Main.getPlugin().getCommand("arena").setExecutor(new ArenaCommand());
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length == 0) {
            commandSender.sendMessage(ChatColor.RED + ERR_HELP);
            return true;
        } else {
            String arg1 = strings[0];
            if(ARGS.contains(arg1)) {
            if(strings.length == 1) {
                switch (arg1) {
                    case "list":
                        showArenas(commandSender);
                        return true;
                    default:
                        commandSender.sendMessage(ChatColor.RED + ERR_HELP);
                        return true;
                }
            } else if(strings.length == 2) {
                    String arg2 = strings[1];
                    switch (arg1) {
                        case "create":
                            createDefaultArena(arg2, commandSender);
                            return true;
                        case "teleport":
                            teleportToArena(arg2, commandSender);
                            return  true;
                        case "load":
                            loadArena(arg2, commandSender);
                            return true;
                        case "unload":
                            unloadArena(arg2, commandSender);
                            return true;
                        case "setspawn1":
                            setSpawn1(arg2, commandSender);
                            return true;
                        case "setspawn2":
                            setSpawn2(arg2, commandSender);
                            return true;
                        default:
                            commandSender.sendMessage(ChatColor.RED + ERR_HELP);
                            return true;
                    }
                }
            }
        }
        return true;
    }

    private static void createDefaultArena(String name, CommandSender commandSender) {
        if(ArenaManager.createArena(name)) {
            commandSender.sendMessage(ChatColor.GREEN + "Create arena [" + name + "]");
        } else {
            commandSender.sendMessage(ChatColor.RED + "This arena already exists");
        }
    }

    private static void teleportToArena(String name, CommandSender commandSender) {
        if(ArenaManager.getArena(name) != null) {
            if(Bukkit.getWorld(name) != null) {
                ((Player) commandSender).teleport(ArenaManager.getArena(name).getSpawn1().getLocation());
            } else {
                commandSender.sendMessage(ChatColor.RED + "This arena is unloaded");
            }
        } else {
            commandSender.sendMessage(ChatColor.RED + "This arena does not exist");
        }
    }

    private static void loadArena(String name, CommandSender commandSender) {
        if(ArenaManager.getArena(name) != null) {
            if(Bukkit.getWorld(name) == null) {
                ArenaManager.getArena(name).load();
                commandSender.sendMessage(ChatColor.GREEN + "Loaded arena [" + name + "]");
            } else {
                commandSender.sendMessage(ChatColor.RED + "This arena already loaded");
            }
        } else {
            commandSender.sendMessage(ChatColor.RED + "This arena does not exist");
        }
    }

    private static void unloadArena(String name, CommandSender commandSender) {
        if(ArenaManager.getArena(name) != null) {
            if(Bukkit.getWorld(name) != null) {
                ArenaManager.getArena(name).unload();
                commandSender.sendMessage(ChatColor.GREEN + "Unloaded arena [" + name + "]");
            } else {
                commandSender.sendMessage(ChatColor.RED + "This arena already unloaded");
            }
        } else {
            commandSender.sendMessage(ChatColor.RED + "This arena does not exist");
        }
    }

    private static void showArenas(CommandSender commandSender) {
        List<Arena> list = ArenaManager.getArenas();
        commandSender.sendMessage(ChatColor.GREEN + "Arenas");
        for(Arena arena : list) {
            if(Bukkit.getWorld(arena.getName()) != null) {
                commandSender.sendMessage(ChatColor.GREEN + arena.toString());
            } else {
                commandSender.sendMessage(ChatColor.GRAY + arena.toString());
            }
        }
    }

    private static void setMapType(String name, String type, CommandSender commandSender) {
        if(ArenaManager.getArena(name) != null) {
            if(MapType.getInteger(type) != -1) {
                Arena arena = ArenaManager.getArena(name);
                arena.setMapType(MapType.getInteger(type));
                commandSender.sendMessage(ChatColor.GREEN + "Set arena maptype [" + name + "] to [" + type + "]");
            } else {
                commandSender.sendMessage(ChatColor.RED + "This maptype does not exist");
            }
        } else {
            commandSender.sendMessage(ChatColor.RED + "This arena does not exist");
        }
    }

    private static void setSpawn1(String name, CommandSender commandSender) {
        if(ArenaManager.getArena(name) != null) {
            Arena arena = ArenaManager.getArena(name);
            arena.setSpawn1(new FixedLocation(((Player) commandSender).getLocation()));
            commandSender.sendMessage(ChatColor.GREEN + "Set arena spawn1 [" + name + "]");
        } else {
            commandSender.sendMessage(ChatColor.RED + "This arena does not exist");
        }
    }

    private static void setSpawn2(String name, CommandSender commandSender) {
        if(ArenaManager.getArena(name) != null) {
            Arena arena = ArenaManager.getArena(name);
            arena.setSpawn2(new FixedLocation(((Player) commandSender).getLocation()));
            commandSender.sendMessage(ChatColor.GREEN + "Set arena spawn2 [" + name + "]");
        } else {
            commandSender.sendMessage(ChatColor.RED + "This arena does not exist");
        }
    }
}
