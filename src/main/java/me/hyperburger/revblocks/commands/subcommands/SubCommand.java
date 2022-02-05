package me.hyperburger.revblocks.commands.subcommands;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public abstract class SubCommand {

    public abstract String getName();
    public abstract String getDescription();
    public abstract String getSyntax();
    public abstract String permission();

    /**
     * Execute and perform commands with this method.
     * @param player The player object.
     * @param args The command's arguments. (Starts from 1)
     * @param plugin the plugin. (Main class)
     */
    public abstract void perform(Player player, String[] args, Plugin plugin);

}
