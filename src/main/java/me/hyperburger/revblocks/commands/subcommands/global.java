package me.hyperburger.revblocks.commands.subcommands;


import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class global extends SubCommand{


    @Override
    public String getName() {
        return "global";
    }

    @Override
    public String getDescription() {
        return "shows the global block count.";
    }

    @Override
    public String getSyntax() {
        return "/revblocks global";
    }

    @Override
    public String permission() {
        return "revblocks.global";
    }

    @Override
    public void perform(Player player, String[] args, Plugin plugin) {

    }
}
