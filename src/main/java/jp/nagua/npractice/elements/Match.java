package jp.nagua.npractice.elements;

import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Match {

    private int num;
    private PracticeKit kit;
    private Arena arena;
    private World world;
    private int duration;
    private List<Player> all_players;
    private List<Player> players1;
    private List<Player> players2;
    private List<Player> alives1;
    private List<Player> alives2;
    private List<Player> specs;
    private String status;

    public Match(PracticeKit kit, Arena arena, List<Player> players1, List<Player> players2) {
        this.num = 0;
        this.kit = kit;
        this.arena = arena;
        WorldCreator worldCreator = new WorldCreator("Map" + (new Random()).nextInt(999999));
        worldCreator.copy(new WorldCreator(arena.getName()));
        this.world = worldCreator.createWorld();
        this.world.setAutoSave(false);
        this.duration = 0;
        this.all_players = new ArrayList<>();
        this.all_players.addAll(players1);
        this.all_players.addAll(players2);
        this.players1 = players1;
        this.players2 = players2;
        this.alives1 = new ArrayList<>();
        alives1.addAll(players1);
        this.alives2 = new ArrayList<>();
        alives2.addAll(players2);
        this.specs = new ArrayList<>();
        this.status = "NotStart";
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public PracticeKit getKit() {
        return kit;
    }

    public void setKit(PracticeKit kit) {
        this.kit = kit;
    }

    public Arena getArena() {
        return arena;
    }

    public void setArena(Arena arena) {
        this.arena = arena;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Player> getAllPlayers() {
        return all_players;
    }

    public void setAllPlayers(List<Player> all_players) {
        this.all_players = all_players;
    }

    public List<Player> getPlayers1() {
        return players1;
    }

    public void setPlayers1(List<Player> players1) {
        this.players1 = players1;
    }

    public List<Player> getPlayers2() {
        return players2;
    }

    public void setPlayers2(List<Player> players2) {
        this.players2 = players2;
    }

    public List<Player> getSpecs() {
        return specs;
    }

    public void setSpecs(List<Player> specs) {
        this.specs = specs;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Player> getAlives2() {
        return alives2;
    }

    public void setAlives2(List<Player> alives2) {
        this.alives2 = alives2;
    }

    public List<Player> getAlives1() {
        return alives1;
    }

    public void setAlives1(List<Player> alives1) {
        this.alives1 = alives1;
    }

    public void removePlayerFromAlives(Player player) {
        alives1.remove(player);
        alives2.remove(player);
    }
}
