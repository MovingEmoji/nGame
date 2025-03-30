package jp.nagua.npractice.utils.managers;

import jp.nagua.npractice.elements.Arena;
import jp.nagua.npractice.types.MapType;
import jp.nagua.npractice.utils.DataHandler;

import java.util.ArrayList;
import java.util.List;

public class ArenaManager {
    public static boolean createArena(String name) {
        if(DataHandler.getCommonDataFromDefault("Map-" + name) == null) {
            DataHandler.putCommonDataToDefault("Map-" + name, new Arena(name, MapType.NORMAL));
            return true;
        }
        return false;
    }

    public static List<Arena> getArenas() {
        List<Arena> list = new ArrayList<>();
        for(Object object : DataHandler.getContainsCommonDataFromDefault("Map-")) {
            list.add((Arena) object);
        }
        return list;
    }

    public static boolean deleteArena(String name) {
        if(DataHandler.getCommonDataFromDefault("Map-" + name) != null) {
            DataHandler.deleteCommonDataFromDefault("Map-" + name);
            return true;
        }
        return false;
    }

    public static Arena getArena(String name) {
        if(DataHandler.getCommonDataFromDefault("Map-" + name) != null) {
            return (Arena) DataHandler.getCommonDataFromDefault("Map-" + name);
        }
        return null;
    }
}
