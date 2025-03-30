package jp.nagua.npractice.utils.managers;

import jp.nagua.npractice.elements.FixedLocation;
import jp.nagua.npractice.elements.serializes.SerializedInventory;
import jp.nagua.npractice.utils.handlers.DataHandler;
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
}
