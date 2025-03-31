package jp.nagua.npractice.utils.managers;

import jp.nagua.npractice.Main;
import jp.nagua.npractice.elements.PracticeKit;
import jp.nagua.npractice.types.QueueType;
import jp.nagua.npractice.utils.handlers.DataHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIManager implements Listener {

    static {
        Bukkit.getServer().getPluginManager().registerEvents(new GUIManager(), Main.getPlugin());
    }

    public static void openUnRankedQueue(Player player) {
        int count = DataHandler.getContainsCommonDataFromDefault("Kit-").size();
        int rows = 1;
        while(count > 9) {
            count -= 9;
            rows++;
        }
        Inventory inventory = Bukkit.createInventory(null, rows * 9,ChatColor.AQUA + "Unranked queue");
        for(Object object : DataHandler.getContainsCommonDataFromDefault("Kit-")) {
            ItemStack item = ((PracticeKit) object).getIcon().getItemStack();
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.AQUA + ((PracticeKit) object).getName());
            item.setItemMeta(meta);
            inventory.addItem(item);
        }
        player.openInventory(inventory);
    }

    public static void openRankedQueue(Player player) {
        int count = DataHandler.getContainsCommonDataFromDefault("Kit-").size();
        int rows = 1;
        while(count > 9) {
            count -= 9;
            rows++;
        }
        Inventory inventory = Bukkit.createInventory(null, rows * 9,ChatColor.RED + "Ranked queue");
        for(Object object : DataHandler.getContainsCommonDataFromDefault("Kit-")) {
            ItemStack item = ((PracticeKit) object).getIcon().getItemStack();
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.RED + ((PracticeKit) object).getName());
            item.setItemMeta(meta);
            inventory.addItem(item);
        }
        player.openInventory(inventory);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(event.getInventory().getName().contains("anked queue")) {
            event.setCancelled(true);
            if(event.getSlot() >= 0 && event.getClickedInventory().equals(event.getWhoClicked().getOpenInventory().getTopInventory())) {
                if(event.getInventory().getItem(event.getSlot()) != null && !event.getInventory().getItem(event.getSlot()).getType().equals(Material.AIR)) {
                    String name = event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().replace("§b", "");
                    if(KitManager.getKit(name) != null) {
                        if(event.getInventory().getName().contains("Unranked queue")) {
                            PlayerManager.joinQueue((Player) event.getWhoClicked(), KitManager.getKit(name), QueueType.UNRANKED);
                        } else if(event.getInventory().getName().contains("Ranked queue")) {

                        }
                    }
                }
            }
        }
    }
}
