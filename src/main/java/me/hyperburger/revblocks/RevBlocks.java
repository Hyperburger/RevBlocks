package me.hyperburger.revblocks;

import me.hyperburger.revblocks.commands.CommandManager;
import me.hyperburger.revblocks.listeners.BlockBreakListener;
import me.hyperburger.revblocks.listeners.PlayerJoinListener;
import me.hyperburger.revblocks.user.User;
import me.hyperburger.revblocks.user.UserHandler;
import org.bukkit.command.Command;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.UUID;

public final class RevBlocks extends JavaPlugin {

    private UserHandler userHandler;
    private FileManager fileManager;
    private final double version = 1.0;

    @Override
    public void onEnable() {

        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();


        // blockRewards.yml
        fileManager = new FileManager(this, this.getConfig(), this.getFile());

        fileManager.setupFile();
        fileManager.setDefaults();
        fileManager.get().options().copyDefaults();
        fileManager.saveFile();
        // ----

        // Startup Message
        System.out.println("  ------------(RevBlocks Plugin)------------  ");
        System.out.println(" ");
        System.out.println("    RevBlock: Successfully Loaded!");
        System.out.println("    Author: HyperBurger");
        System.out.println("    version = " + version);
        System.out.println(" ");
        System.out.println("  -------------------------------------  ");

        userHandler = new UserHandler(this);

        // Registers
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(this, userHandler), this);
        this.getServer().getPluginManager().registerEvents(new BlockBreakListener(this, userHandler), this);

        this.getCommand("revblocks").setExecutor(new CommandManager(this));

        // Restore Data
        if (this.getConfig().contains("data")) {
            restoreData();
            this.getConfig().set("data", null); // Clear the config.yml from all information.
            this.saveConfig(); // Save to confirm clear.
        }

    }

    @Override
    public void onDisable() {
        saveData();
    }



    private void saveData(){
        if (!userHandler.getUsersMap().isEmpty()) { // Check that the map isn't empty.
            for (Map.Entry<UUID, User> entry : userHandler.getUsersMap().entrySet()) { // Loop through the map values.
                this.getConfig().set("data." + entry.getKey().toString() + ".blockCount", entry.getValue().getBlockCount()); // Set the block count.
                this.getConfig().set("data." + entry.getKey().toString() + ".name", entry.getValue().getName()); // Set the player's name.
            }
            this.saveConfig();
        }
    }

    private void restoreData(){

        ConfigurationSection dataSection = this.getConfig().getConfigurationSection("data"); // Access the data:

        if (dataSection == null) return; // Null check

        // Loop through all the keys under data:
        for (String key : dataSection.getKeys(false)) {
            ConfigurationSection uniqueData = dataSection.getConfigurationSection(key);

            UUID uuid = UUID.fromString(key); // Player's UUID.
            String name = uniqueData.getString("name"); // Player's Name.
            int blockCount = uniqueData.getInt("blockCount"); // Player's block count.

            // Create a new user object and put it into the map after reset.
            User user = new User(uuid, name, blockCount);
            userHandler.getUsersMap().put(uuid, user);
        }
    }

    public FileManager getFileManager() {
        return fileManager;
    }
    public UserHandler getUserHandler() {return userHandler;}
}

