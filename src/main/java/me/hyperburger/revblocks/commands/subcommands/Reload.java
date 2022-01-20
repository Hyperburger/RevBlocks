package me.hyperburger.revblocks.commands.subcommands;

import me.hyperburger.revblocks.RevBlocks;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Reload extends SubCommand{

    private final RevBlocks revBlocks;
    public Reload(RevBlocks revBlocks){
        this.revBlocks = revBlocks;
    }


    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "Reloads all the plugin files.";
    }

    @Override
    public String getSyntax() {
        return "/revblocks reload";
    }

    @Override
    public void perform(Player player, String[] args, Plugin plugin) {
        plugin.reloadConfig();
        revBlocks.getFileManager().reloadFile();
    }
}
