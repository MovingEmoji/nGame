package jp.nagua.npractice.listener;

import jp.nagua.npractice.elements.serializes.SerializedItem;
import jp.nagua.npractice.utils.handlers.DataHandler;
import jp.nagua.npractice.utils.managers.GUIManager;
import jp.nagua.npractice.utils.managers.PlayerManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.time.LocalDateTime;

public class EventListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        DataHandler.putPlayerDataToDefault(event.getPlayer(), "LoginTime", LocalDateTime.now());
        PlayerManager.initializePlayer(event.getPlayer());
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if(!event.getPlayer().getItemInHand().getType().equals(Material.AIR)) {
            if(event.getPlayer().getItemInHand().equals(((SerializedItem) DataHandler.getCommonDataFromDefault("Trigger-OpenUnRankedQueue")).getItemStack())) {
                event.setCancelled(true);
                GUIManager.openUnRankedQueue(event.getPlayer());
            } else if(event.getPlayer().getItemInHand().equals(((SerializedItem) DataHandler.getCommonDataFromDefault("Trigger-OpenRankedQueue")).getItemStack())) {
                event.setCancelled(true);
                GUIManager.openRankedQueue(event.getPlayer());
            } else if(event.getPlayer().getItemInHand().equals(((SerializedItem) DataHandler.getCommonDataFromDefault("Trigger-LeaveQueue")).getItemStack())) {
                event.setCancelled(true);
                PlayerManager.leaveQueue(event.getPlayer());
            }
        }
    }
}
