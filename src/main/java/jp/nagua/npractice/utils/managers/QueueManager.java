package jp.nagua.npractice.utils.managers;

import jp.nagua.npractice.Main;
import jp.nagua.npractice.elements.PracticeKit;
import jp.nagua.npractice.elements.Queue;
import jp.nagua.npractice.types.QueueType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class QueueManager {

    private static Map<String, List<Player>> queues;
    private static Map<UUID, Queue> queue_data;

    public static void initializeQueues() {
        queues = new HashMap<>();
        queue_data = new HashMap<>();
        for (PracticeKit kit : KitManager.getKits()) {
            queues.put(QueueType.UNRANKED + kit.getName(), new ArrayList<>());
        }
        for (PracticeKit kit : KitManager.getKits()) {
            queues.put(QueueType.RANKED + kit.getName(), new ArrayList<>());
        }
        Bukkit.getScheduler().runTaskTimer(Main.getPlugin(), () -> {
            for(Map.Entry<String, List<Player>> entry : queues.entrySet()) {
                while(entry.getValue().size() > 1) {
                    if(entry.getValue().get(0) != null && entry.getValue().get(1) != null) {
                        PlayerManager.leaveQueue(entry.getValue().get(1));
                        PlayerManager.leaveQueue(entry.getValue().get(0));
                    }
                }
            }
        }, 0, 60);
    }

    public static void addPlayerToQueue(PracticeKit kit, QueueType type, Player player) {
        if(queues.get(type + kit.getName()) != null) {
            queues.get(type + kit.getName()).add(player);
            queue_data.put(player.getUniqueId(), new Queue(kit, type));
        }
    }

    public static Queue getPlayerQueue(Player player) {
        return queue_data.get(player.getUniqueId());
    }

    public static void removePlayerFromQueue(Player player) {
        if(getPlayerQueue(player) != null) {
            queues.get(getPlayerQueue(player).getQueueType() + getPlayerQueue(player).getKit().getName()).remove(player);
            queue_data.remove(player.getUniqueId());
        }
    }

}


