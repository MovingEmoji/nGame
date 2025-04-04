package jp.nagua.npractice.elements;

import jp.nagua.npractice.elements.serializes.SerializedInventory;
import jp.nagua.npractice.elements.serializes.SerializedItem;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.Serializable;

public class PracticeKit implements Serializable {

    private String name;
    private int flag;
    private int map_type;
    private int round;
    private SerializedItem icon;
    private SerializedInventory inventory;

    public PracticeKit(String name, int flag, int map_type, int round, ItemStack icon, Inventory inventory) {
        this.name = name;
        this.flag = flag;
        this.map_type = map_type;
        this.round = round;
        this.icon = new SerializedItem(icon);
        this.inventory = new SerializedInventory(name, inventory);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getMapType() {
        return map_type;
    }

    public void setMapType(int map_type) {
        this.map_type = map_type;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public SerializedItem getIcon() {
        return icon;
    }

    public void setIcon(SerializedItem icon) {
        this.icon = icon;
    }

    public SerializedInventory getInventory() {
        return inventory;
    }

    public void setInventory(SerializedInventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return getName();
    }
}
