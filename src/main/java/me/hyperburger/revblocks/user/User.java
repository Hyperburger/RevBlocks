package me.hyperburger.revblocks.user;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public final class User {

    private final UUID uuid;
    private String name;
    private int blockCount;

    public User(UUID uuid, String name, int blockCount){
        this.name =  name;
        this.uuid = uuid;
        this.blockCount = blockCount;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getBlockCount() {
        return blockCount;
    }

    public void setBlockCount(int blockCount) {
        this.blockCount = blockCount;
    }

    public void sendMessage(final String message){
        Objects.requireNonNull(Bukkit.getPlayer(uuid)).sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
