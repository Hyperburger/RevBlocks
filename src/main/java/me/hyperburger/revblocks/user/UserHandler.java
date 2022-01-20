package me.hyperburger.revblocks.user;

import me.hyperburger.revblocks.RevBlocks;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class UserHandler {

    public HashMap<UUID, User> users = new HashMap<>();
    private final RevBlocks plugin;

    public UserHandler(RevBlocks plugin){
        this.plugin = plugin;
    }

    public void register(final UUID uuid, String name, final int blocks){
        users.put(uuid, new User(uuid, name, blocks));
    }

    public boolean exists(final UUID uuid){
        return users.containsKey(uuid);}

    public HashMap<UUID, User> getUsersMap(){
        return users;
    }

    public User findUser(UUID uuid){
        return users.get(uuid);}

    public boolean getUserFromConfig(Configuration configuration, Player player){
        final ConfigurationSection userSection = configuration.getConfigurationSection("data");
        for (String key : userSection.getKeys(false)){
            ConfigurationSection userId = userSection.getConfigurationSection(key);
            String uuid = userId.getString("uuid");

            return player.getUniqueId().toString().equalsIgnoreCase(uuid);
        }
        return false;
    }
}


