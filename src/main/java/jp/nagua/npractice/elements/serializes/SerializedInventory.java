package jp.nagua.npractice.elements.serializes;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.*;

public class SerializedInventory implements Serializable {

    private String name;
    private String inventory;

    public SerializedInventory(String name, Inventory inventory) {
        this.name = name;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            BukkitObjectOutputStream bukkitObjectOutputStream = new BukkitObjectOutputStream(byteArrayOutputStream);
            bukkitObjectOutputStream.writeObject(inventory.getContents());
            this.inventory = Base64Coder.encodeLines(byteArrayOutputStream.toByteArray());
            bukkitObjectOutputStream.close();
            byteArrayOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getInventory() {
        return inventory;
    }

    public String getName() {
        return name;
    }

    public static void loadInventory(Inventory inventory, SerializedInventory serializedInventory) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64Coder.decodeLines(serializedInventory.getInventory()));
        try {
            BukkitObjectInputStream bukkitObjectInputStream = new BukkitObjectInputStream(byteArrayInputStream);
            ItemStack[] items = (ItemStack[]) bukkitObjectInputStream.readObject();
            for(int i = 0; i < inventory.getSize(); i++) {
                inventory.setItem(i, items[i]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static void loadInventory(Player player, SerializedInventory serializedInventory) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64Coder.decodeLines(serializedInventory.getInventory()));
        try {
            BukkitObjectInputStream bukkitObjectInputStream = new BukkitObjectInputStream(byteArrayInputStream);
            ItemStack[] items = (ItemStack[]) bukkitObjectInputStream.readObject();
            int i;
            for(i = 0; i < player.getInventory().getSize(); i++) {
                player.getInventory().setItem(i, items[i]);
            }
            if(player.getInventory().getSize() < items.length) {
                player.getInventory().setHelmet(items[i]);
                player.getInventory().setChestplate(items[i + 1]);
                player.getInventory().setLeggings(items[i + 2]);
                player.getInventory().setBoots(items[i + 3]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
