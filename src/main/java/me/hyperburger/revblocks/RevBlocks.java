package me.hyperburger.revblocks;

import me.hyperburger.revblocks.commands.CommandManager;
import me.hyperburger.revblocks.commands.TabComplete;
import me.hyperburger.revblocks.files.FileManager;
import me.hyperburger.revblocks.files.FileSettings;
import me.hyperburger.revblocks.listeners.BlockBreakListener;
import me.hyperburger.revblocks.listeners.PlayerJoinListener;
import me.hyperburger.revblocks.placeholders.BlockPlaceHolder;
import me.hyperburger.revblocks.user.User;
import me.hyperburger.revblocks.user.UserHandler;
import me.hyperburger.revblocks.utilis.Updater;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.UUID;

public final class RevBlocks extends JavaPlugin {

    private UserHandler userHandler;
    private FileManager fileManager;
    private FileSettings fileSettings;

    @Override
    public void onEnable() {
        this.startMessage();

        new Updater(this, 93449).getLatestVersion(ver -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(ver)){
                System.out.println("[RevBlocks] Your plugin version is up to date.");
            } else {
                System.out.println("[RevBlocks] ATTENTION YOU ARE NOT USING THE LATEST VERSION.");
            }
        });

        // ---- Register Config ---- \\
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();

        // ---- Register UserHandler Class ---- \\
        userHandler = new UserHandler(this);

        // ---- Enable PlaceHolderAPI Support ---- \\
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
            new BlockPlaceHolder(userHandler).register();
        } else System.out.println("PlaceholderAPI is not installed.");


        // blockRewards.yml | messages.yml
        fileManager = new FileManager(this, this.getConfig(), this.getFile());

        fileManager.setupFile();
        fileManager.setDefaults();
        fileManager.get().options().copyDefaults();
        fileManager.saveFile();

        fileSettings = new FileSettings(this, this.getConfig(), this.getFile());

        fileSettings.setupFile();
        fileSettings.setDefaults();
        fileSettings.get().options().copyDefaults();
        fileSettings.saveFile();
        // ----

        // Registers
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(this, userHandler), this);
        this.getServer().getPluginManager().registerEvents(new BlockBreakListener(this, userHandler), this);

        this.getCommand("revblocks").setTabCompleter(new TabComplete(new CommandManager(this)));
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

    private void startMessage(){

        // Startup Message
        System.out.println("  ------------(RevBlocks Plugin)------------  ");
        System.out.println(" ");
        System.out.println("    RevBlock: Successfully Loaded!");
        System.out.println("    Author: HyperBurger");
        System.out.println("    version = " + this.getDescription().getVersion());
        System.out.println(" ");
        System.out.println("  -------------------------------------  ");

    }

    public FileManager getFileManager() {
        return fileManager;
    }
    public FileSettings getFileSettings() {return fileSettings;}
    public UserHandler getUserHandler() {return userHandler;}
}

