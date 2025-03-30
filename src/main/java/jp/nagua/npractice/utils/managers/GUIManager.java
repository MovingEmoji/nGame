package jp.nagua.npractice.utils.managers;

import jp.nagua.npractice.Main;
import jp.nagua.npractice.elements.PracticeKit;
import jp.nagua.npractice.utils.handlers.DataHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
        }
    }
}
