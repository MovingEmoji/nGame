package jp.nagua.npractice.utils.handlers;

import jp.nagua.npractice.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataHandler implements Serializable {

    public static void encodeAs(Map data, String filename) throws IOException {
        File file = new File(Main.getPlugin().getDataFolder() + "\\" + filename);
        if(!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(data);
        objectOutputStream.flush();
        objectOutputStream.close();
        fileOutputStream.close();
    }

    public static Map decodeFrom(String filename) throws IOException, ClassNotFoundException {
        File file = new File(Main.getPlugin().getDataFolder() + "\\" + filename);
        if(!file.exists()) {
            return new HashMap();
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Map map = (Map) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
        return map;
    }

    public static void putPlayerData(Map data, Player player, String key, Object value) {
        data.put(player.getUniqueId().toString() + key, value);
    }

    public static void putPlayerDataToDefault(Player player, String key, Object value) {
        Main.dataMap.put(player.getUniqueId().toString() + key, value);
    }

    public static void putCommonDataToDefault(String key, Object value) {
        Main.dataMap.put(key, value);
    }

    public static Object getCommonDataFromDefault(String key) {
        return Main.dataMap.get(key);
    }

    public static void deleteCommonDataFromDefault(String key) {
        Main.dataMap.remove(key);
    }

    public static List<Object> getContainsCommonDataFromDefault(String key) {
        List<Object> list = new ArrayList<>();
        for(Map.Entry<String, Object> entry : Main.dataMap.entrySet()) {
            if(entry.getKey().contains(key)) {
                list.add(entry.getValue());
            }
        }
        return list;
    }

    public static void loadDefaultData() {
        try {
            Main.dataMap = DataHandler.decodeFrom("data.dat");
            if(!Main.dataMap.isEmpty()) {
                for(Map.Entry<String, Object> entry : Main.dataMap.entrySet()) {
                    Bukkit.getLogger().info("[VARS] " + entry.getKey().toString() + " : " + entry.getValue());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveDefaultData() {
        try {
            DataHandler.encodeAs(Main.dataMap, "data.dat");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
