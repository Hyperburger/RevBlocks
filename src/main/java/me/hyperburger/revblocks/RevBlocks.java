package me.hyperburger.revblocks;

import me.hyperburger.revblocks.listeners.BlockBreakListener;
import me.hyperburger.revblocks.listeners.PlayerJoinListener;
import me.hyperburger.revblocks.tasks.SaveTask;
import me.hyperburger.revblocks.user.UserHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class RevBlocks extends JavaPlugin {

    private UserHandler userHandler;

    @Override
    public void onEnable() {
        // Plugin startup logic

        saveDefaultConfig();
        getConfig().options().copyDefaults(true);

        userHandler = new UserHandler(this);

        saveConfig();

        //this.getServer().getPluginManager().registerEvents(new BlockBreakListener(this, userHandler), this);
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(this, userHandler), this);
        this.getServer().getPluginManager().registerEvents(new BlockBreakListener(this, userHandler), this);

        new SaveTask(this, userHandler).runTaskTimerAsynchronously(this, 10, 30);

    }

    @Override
    public void onDisable() {


    }
}
