package jp.nagua.nGame.commands;

import jp.nagua.nGame.Main;
import jp.nagua.nGame.elements.SerializedInventory;
import jp.nagua.nGame.elements.SerializedItem;
import jp.nagua.nGame.types.KitFlag;
import jp.nagua.nGame.types.MapType;
import jp.nagua.nGame.elements.PracticeKit;
import jp.nagua.nGame.utils.DataHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KitCommand implements CommandExecutor {

    private static final ArrayList<String> ARGS = new ArrayList<>(Arrays.asList(
            "create",
            "delete",
            "list",
            "help",
            "setinventory",
            "seticon",
            "setflag",
            "setmaptype",
            "setround",
            "loadinventory"
    ));

    static {
        Main.getPlugin().getCommand("kit").setExecutor(new KitCommand());
        Main.getPlugin().getCommand("kit").setTabCompleter((commandSender, command, s, strings) -> {
            commandSender.sendMessage(strings.length + "");
            if(strings.length == 1) {
                return ARGS;
            }
            if(strings.length == 2) {
                List<String> list = new ArrayList<>();
                for(Object object : DataHandler.getContainsCommonDataFromDefault("Kit-")) {
                    list.add(object.toString());
                }
                return list;
            }
            if(strings.length == 3) {
                String arg1 = strings[0];
                if(arg1.equals("setflag")) {
                    int i = 0;
                    List<String> list = new ArrayList<>();
                    while(!KitFlag.getString(i).equals("NOTFOUND")) {
                        list.add(KitFlag.getString(i));
                        i ++;
                    }
                } else if(arg1.equals("setmaptype")) {
                    int i = 0;
                    List<String> list = new ArrayList<>();
                    while(!MapType.getString(i).equals("NOTFOUND")) {
                        list.add(MapType.getString(i));
                        i ++;
                    }
                }
            }
            return List.of();
        });
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
                    if(strings.length == 1) {
                        switch (arg1) {
                            case "list":
                                showKits(commandSender);
                                return true;
                            case "help":
                                showHelp(commandSender);
                            default:
                                commandSender.sendMessage(ChatColor.RED + "Please type /kit help");
                                return true;
                        }
                    } else if(strings.length == 2) {
                        String arg2 = strings[1];
                        switch (arg1) {
                            case "create":
                                createDefaultKit(arg2, commandSender);
                                return true;
                            case "delete":
                                deleteKit(arg2, commandSender);
                                return true;
                            case "setinventory":
                                setKitInventory(arg2, commandSender);
                                return true;
                            case "loadinventory":
                                loadKitInventory(arg2, commandSender);
                                return true;
                            case "seticon":
                                setKitIcon(arg2, commandSender);
                                return true;
                            default:
                                commandSender.sendMessage(ChatColor.RED + "Please type /kit help");
                                return true;
                        }
                    } else if(strings.length == 3) {
                        String arg2 = strings[1];
                        String arg3 = strings[2];
                        switch (arg1) {
                            case "setflag":
                                setKitFlag(arg2, arg3, commandSender);
                                return true;
                            case "setmaptype":
                                setKitMapType(arg2, arg3, commandSender);
                                return true;
                            default:
                                commandSender.sendMessage(ChatColor.RED + "Please type /kit help");
                                return true;
                        }
                    }
                } else {
                    commandSender.sendMessage(ChatColor.RED + "Please type /kit help");
                    return true;
                }
            }

        }
        return false;
    }

    private static void createDefaultKit(String name, CommandSender commandSender) {
        if(DataHandler.getCommonDataFromDefault("Kit-" + name) == null) {
            Inventory inventory = Bukkit.getServer().createInventory(null, 45);
            DataHandler.putCommonDataToDefault("Kit-" + name,
                    new PracticeKit(name, KitFlag.NORMAL, MapType.NORMAL, 1, ((Player) commandSender).getItemInHand(), inventory));
            commandSender.sendMessage(ChatColor.GREEN + "Create kit [" + name + "]");
        } else {
            commandSender.sendMessage(ChatColor.RED + "This kit already exists");
        }
    }

    private static void deleteKit(String name, CommandSender commandSender) {
        if(DataHandler.getCommonDataFromDefault("Kit-" + name) != null) {
            DataHandler.deleteCommonDataFromDefault("Kit-" + name);
            commandSender.sendMessage(ChatColor.YELLOW + "Delete kit [" + name + "]");
        } else {
            commandSender.sendMessage(ChatColor.RED + "This kit does not exist");
        }
    }

    private static void showKits(CommandSender commandSender) {
        List<Object> kits = DataHandler.getContainsCommonDataFromDefault("Kit-");
        commandSender.sendMessage(ChatColor.GREEN + "Kits");
        for(Object object : kits) {
            PracticeKit kit = (PracticeKit) object;
            commandSender.sendMessage(kit.getName());
        }
    }

    private static void setKitInventory(String name, CommandSender commandSender) {
        if(DataHandler.getCommonDataFromDefault("Kit-" + name) != null) {
            PracticeKit kit = (PracticeKit) DataHandler.getCommonDataFromDefault("Kit-" + name);
            Inventory inventory = Bukkit.getServer().createInventory(null, 45);
            inventory.setContents(((Player) commandSender).getInventory().getContents());
            inventory.setItem(36, ((Player) commandSender).getInventory().getHelmet());
            inventory.setItem(37, ((Player) commandSender).getInventory().getChestplate());
            inventory.setItem(38, ((Player) commandSender).getInventory().getLeggings());
            inventory.setItem(39, ((Player) commandSender).getInventory().getBoots());
            kit.setInventory(new SerializedInventory(kit.getName(), inventory));
            commandSender.sendMessage(ChatColor.GREEN + "Set kit inventory [" + name + "]");
        } else {
            commandSender.sendMessage(ChatColor.RED + "This kit does not exist");
        }
    }

    private static void loadKitInventory(String name, CommandSender commandSender) {
        if(DataHandler.getCommonDataFromDefault("Kit-" + name) != null) {
            PracticeKit kit = (PracticeKit) DataHandler.getCommonDataFromDefault("Kit-" + name);
            SerializedInventory.loadInventory((Player) commandSender, kit.getInventory());
            commandSender.sendMessage(ChatColor.GREEN + "Load kit inventory [" + name + "]");
        } else {
            commandSender.sendMessage(ChatColor.RED + "This kit does not exist");
        }
    }

    private static void setKitIcon(String name, CommandSender commandSender) {
        if(DataHandler.getCommonDataFromDefault("Kit-" + name) != null) {
            PracticeKit kit = (PracticeKit) DataHandler.getCommonDataFromDefault("Kit-" + name);
            kit.setIcon(new SerializedItem(((Player) commandSender).getItemInHand()));
        } else {
            commandSender.sendMessage(ChatColor.RED + "This kit does not exist");
        }
    }

    private static void setKitFlag(String name, String flag, CommandSender commandSender) {
        if(DataHandler.getCommonDataFromDefault("Kit-" + name) != null) {
            PracticeKit kit = (PracticeKit) DataHandler.getCommonDataFromDefault("Kit-" + name);
            if(KitFlag.getInteger(flag) != -1) {
                kit.setFlag(KitFlag.getInteger(flag));
                commandSender.sendMessage(ChatColor.GREEN + "Set kit flag [" + name + "] to [" + flag + "]");
            } else {
                commandSender.sendMessage(ChatColor.RED + "This flag does not exist");
            }
        } else {
            commandSender.sendMessage(ChatColor.RED + "This kit does not exist");
        }
    }

    private static void setKitMapType(String name, String type, CommandSender commandSender) {
        if(DataHandler.getCommonDataFromDefault("Kit-" + name) != null) {
            PracticeKit kit = (PracticeKit) DataHandler.getCommonDataFromDefault("Kit-" + name);
            if(MapType.getInteger(type) != -1) {
                kit.setFlag(MapType.getInteger(type));
                commandSender.sendMessage(ChatColor.GREEN + "Set kit maptype [" + name + "] to [" + type + "]");
            } else {
                commandSender.sendMessage(ChatColor.RED + "This maptype does not exist");
            }
        } else {
            commandSender.sendMessage(ChatColor.RED + "This kit does not exist");
        }
    }

    private static void setKitRound(String name, int round, CommandSender commandSender) {
        if(DataHandler.getCommonDataFromDefault("Kit-" + name) != null) {
            PracticeKit kit = (PracticeKit) DataHandler.getCommonDataFromDefault("Kit-" + name);
            if(round > 0) {
                kit.setRound(round);
                commandSender.sendMessage(ChatColor.GREEN + "Set kit round [" + name + "] to [" + round + "]");
            } else {
                commandSender.sendMessage(ChatColor.RED + "Round should be above 1");
            }
        } else {
            commandSender.sendMessage(ChatColor.RED + "This kit does not exist");
        }
    }

    private static void showHelp(CommandSender commandSender) {
        commandSender.sendMessage(ChatColor.GREEN + "/kit help   " + ChatColor.GRAY + "Show this message");
        commandSender.sendMessage(ChatColor.GREEN + "/kit create [KitName]   " + ChatColor.GRAY + "Create new kit");
        commandSender.sendMessage(ChatColor.GREEN + "/kit delete [KitName]   " + ChatColor.GRAY + "Delete kit");
        commandSender.sendMessage(ChatColor.GREEN + "/kit list   " + ChatColor.GRAY + "Show kits list");
        commandSender.sendMessage(ChatColor.GREEN + "/kit setflag   " + ChatColor.GRAY + "Set kit flag");
        commandSender.sendMessage(ChatColor.GREEN + "/kit setmaptype   " + ChatColor.GRAY + "Set kit maptype");
        commandSender.sendMessage(ChatColor.GREEN + "/kit setround   " + ChatColor.GRAY + "Set kit round");
    }
}
