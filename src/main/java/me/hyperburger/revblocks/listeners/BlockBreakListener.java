package me.hyperburger.revblocks.listeners;

import me.hyperburger.revblocks.RevBlocks;
import me.hyperburger.revblocks.user.User;
import me.hyperburger.revblocks.user.UserHandler;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {


    private final RevBlocks plugin;
    private final UserHandler userHandler;

    public BlockBreakListener(RevBlocks plugin, UserHandler userHandler){
        this.plugin = plugin;
        this.userHandler = userHandler;
    }

    @EventHandler
    public void onBlockBreak(final BlockBreakEvent event){

        // Get the player and the block broken.
        final Player player = event.getPlayer();
        final Block block = event.getBlock();

        // Get the user by UUID.
        final User user = userHandler.findUser(player.getUniqueId());

        // Increment the user's blocks broken.
        user.setBlockCount(user.getBlockCount() + 1);
        user.sendMessage("&bBlocks Broken: " + user.getName() + user.getBlockCount());

    }
}
