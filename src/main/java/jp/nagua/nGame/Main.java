package jp.nagua.nGame;

import jp.nagua.nGame.utils.ClassHandler;
import jp.nagua.nGame.utils.DataHandler;
import jp.nagua.nGame.listener.EventListener;
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
        ClassHandler.loadClasses("jp.nagua.nGame.commands");

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
