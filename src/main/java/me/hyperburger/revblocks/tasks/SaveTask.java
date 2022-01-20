package me.hyperburger.revblocks.tasks;

import me.hyperburger.revblocks.RevBlocks;
import me.hyperburger.revblocks.user.User;
import me.hyperburger.revblocks.user.UserHandler;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class SaveTask extends BukkitRunnable {

    private final RevBlocks plugin;
    private final UserHandler userHandler;

    public SaveTask(RevBlocks plugin, UserHandler userHandler){
        this.plugin = plugin;
        this.userHandler = userHandler;
    }

    @Override
    public void run() {

        // Config.
        FileConfiguration config = plugin.getConfig();
        ConfigurationSection cs = config.getConfigurationSection("Users");

        // Loop through the map.
        for (UUID uuid : userHandler.getUsersMap().keySet()){

            // Get the user.
            User user = userHandler.findUser(uuid);

            String name = user.getName(); // User's name.
            int blockCount = user.getBlockCount(); // User's block count.

            // Save the data.
            cs.set(name + ".uuid", user.getUuid().toString());
            cs.set(name + ".blockCount", blockCount);

            // Save the config and debug.
            plugin.saveConfig();
            Bukkit.broadcastMessage("Task saved, saved users");

        }
    }
}
