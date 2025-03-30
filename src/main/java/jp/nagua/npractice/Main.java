package jp.nagua.npractice;

import jp.nagua.npractice.utils.ClassHandler;
import jp.nagua.npractice.utils.DataHandler;
import jp.nagua.npractice.listener.EventListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public class Main extends JavaPlugin {

    public static Map<String, Object> dataMap;
    public static ClassLoader classLoader;

    @Override
    public void onEnable() {

        classLoader = getClassLoader();
        this.getDataFolder().mkdir();
        DataHandler.loadDefaultData();
        getServer().getPluginManager().registerEvents(new EventListener(), this);
        ClassHandler.loadClasses("jp.nagua.npractice.commands");

    }
    @Override
    public void onDisable() {
        DataHandler.saveDefaultData();
    }

    public static Main getPlugin() {
        return JavaPlugin.getPlugin(Main.class);
    }

    public static ClassLoader getPluginClassLoader() { return classLoader; }

}
