package jp.nagua.npractice.utils.managers;

import jp.nagua.npractice.elements.PracticeKit;
import jp.nagua.npractice.utils.handlers.DataHandler;

import java.util.ArrayList;
import java.util.List;

public class KitManager {
    public static List<PracticeKit> getKits() {
        List<PracticeKit> list = new ArrayList<>();
        for(Object object : DataHandler.getContainsCommonDataFromDefault("Kit-")) {
            list.add((PracticeKit) object);
        }
        return list;
    }

    public static PracticeKit getKit(String name) {
        if(DataHandler.getCommonDataFromDefault("Kit-" + name) != null) {
            return (PracticeKit) DataHandler.getCommonDataFromDefault("Kit-" + name);
        }
        return null;
    }
}
