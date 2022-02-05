package me.hyperburger.revblocks.commands.subcommands;

import me.hyperburger.revblocks.RevBlocks;
import me.hyperburger.revblocks.utilis.Utilis;
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
    public String permission() {
        return "revblocks.reload";
    }

    @Override
    public void perform(Player player, String[] args, Plugin plugin) {

        player.sendMessage(Utilis.hexMessage("&aSuccessfully reloaded all the plugin files."));
        plugin.reloadConfig();

        // Reload Custom files
        revBlocks.getFileManager().reloadFile();
        revBlocks.getFileSettings().reloadFile();
    }
}
