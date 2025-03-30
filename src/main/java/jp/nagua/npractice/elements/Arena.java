package jp.nagua.npractice.elements;

import org.bukkit.*;

import java.io.Serializable;

public class Arena implements Serializable {

    private String name;
    private int map_type;
    private FixedLocation spawn1;
    private FixedLocation spawn2;

    public Arena(String name, int map_type) {
        this.name = name;
        this.map_type = map_type;
        WorldCreator worldCreator = new WorldCreator(name);
        worldCreator.type(WorldType.FLAT);
        worldCreator.generatorSettings("2;0;1;");
        World world = worldCreator.createWorld();
        world.setAutoSave(true);
        world.setDifficulty(Difficulty.NORMAL);
        world.setGameRuleValue("doFireTick", "false");
        world.setGameRuleValue("doDayLightCycle", "false");
        world.setGameRuleValue("doMobSpawning", "false");
        this.spawn1 = new FixedLocation(world.getBlockAt(0, 50, 0).getLocation());
        this.spawn2 = new FixedLocation(world.getBlockAt(0, 50, 0).getLocation());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMapType() {
        return map_type;
    }

    public void setMapType(int map_type) {
        this.map_type = map_type;
    }

    public FixedLocation getSpawn1() {
        return spawn1;
    }

    public void setSpawn1(FixedLocation spawn1) {
        this.spawn1 = spawn1;
    }

    public FixedLocation getSpawn2() {
        return spawn2;
    }

    public void setSpawn2(FixedLocation spawn2) {
        this.spawn2 = spawn2;
    }

    public void load() {
        WorldCreator worldCreator = new WorldCreator(name);
        worldCreator.createWorld();
    }

    public void unload() {
        Bukkit.unloadWorld(name, true);
    }

    @Override
    public String toString() {
        return getName();
    }
}
