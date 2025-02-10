package jp.nagua.nGame.listener;

import jp.nagua.nGame.Main;
import jp.nagua.nGame.elements.FixedLocation;
import jp.nagua.nGame.elements.SerializedInventory;
import jp.nagua.nGame.utils.DataHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.time.LocalDateTime;

public class EventListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        DataHandler.putPlayerDataToDefault(event.getPlayer(), "LoginTime", LocalDateTime.now());
        if(DataHandler.getCommonDataFromDefault("Lobby") != null) {
            event.getPlayer().teleport(((FixedLocation) DataHandler.getCommonDataFromDefault("Lobby")).getLocation());
        }
        if(DataHandler.getCommonDataFromDefault("LobbyInventory") != null) {
            SerializedInventory.loadInventory(event.getPlayer(), (SerializedInventory) DataHandler.getCommonDataFromDefault("LobbyInventory"));
        }
    }
}
