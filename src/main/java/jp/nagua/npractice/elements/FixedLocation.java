package jp.nagua.npractice.elements;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.Serializable;

public class FixedLocation implements Serializable {

    private String world;
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;

    public FixedLocation(Location location) {
        this.world = location.getWorld().getName();
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
        this.yaw = location.getYaw();
        this.pitch = location.getPitch();
    }

    public Location getLocation() {
        return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
    }

}
