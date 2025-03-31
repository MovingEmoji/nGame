package jp.nagua.npractice.utils.managers;

import jp.nagua.npractice.elements.FixedLocation;
import jp.nagua.npractice.elements.PracticeKit;
import jp.nagua.npractice.elements.Queue;
import jp.nagua.npractice.elements.serializes.SerializedInventory;
import jp.nagua.npractice.types.QueueType;
import jp.nagua.npractice.utils.handlers.DataHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class PlayerManager {
    public static void initializePlayer(Player player) {
        if(DataHandler.getCommonDataFromDefault("Location-Lobby") != null) {
            player.teleport(((FixedLocation) DataHandler.getCommonDataFromDefault("Location-Lobby")).getLocation());
        }
        if(DataHandler.getCommonDataFromDefault("Inventory-Lobby") != null) {
            SerializedInventory.loadInventory(player, (SerializedInventory) DataHandler.getCommonDataFromDefault("Inventory-Lobby"));
        }
    }

    public static void joinQueue(Player player, PracticeKit kit, QueueType type) {
        QueueManager.addPlayerToQueue(kit, type, player);
        player.closeInventory();
        if(DataHandler.getCommonDataFromDefault("Inventory-Queue") != null) {
            SerializedInventory.loadInventory(player, (SerializedInventory) DataHandler.getCommonDataFromDefault("Inventory-Queue"));
        } else {
            player.getInventory().clear();
        }
        player.sendMessage(ChatColor.GREEN + "You joined the " + type.getType() + " " + kit.getName() + " queue");
    }

    public static void leaveQueue(Player player) {
        if(QueueManager.getPlayerQueue(player) != null) {
            String kit_name = QueueManager.getPlayerQueue(player).getKit().getName();
            String type = QueueManager.getPlayerQueue(player).getQueueType().getType();
            QueueManager.removePlayerFromQueue(player);
            player.sendMessage(ChatColor.RED + "You left the " + type + " " + kit_name + " queue");
            initializePlayer(player);
        }
    }
}
