package me.hyperburger.revblocks.listeners;

import me.hyperburger.revblocks.RevBlocks;
import me.hyperburger.revblocks.user.User;
import me.hyperburger.revblocks.user.UserHandler;
import me.hyperburger.revblocks.utilis.Utilis;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.List;

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
            if (user.getBlockCount() <= 0) user.setBlockCount(1);

            user.incBlockCount();
            user.sendMessage("&eBlocks Broken: &f" + user.getName() + " " + user.getBlockCount()); // Debug Message

            // Reward the player when reaching X amount of blocks mined.
            ConfigurationSection rewardSection = this.plugin.getFileManager().get().getConfigurationSection("Rewards");
            if (rewardSection == null) return;

            for (String key : rewardSection.getKeys(false)) {
                ConfigurationSection uniqueReward = rewardSection.getConfigurationSection(key);

                int blocksNeeded = uniqueReward.getInt("blocksNeeded");
                List<String> commandList = uniqueReward.getStringList("commands");

                if (user.getBlockCount() == blocksNeeded){
                    for (String commands : commandList){
                        Utilis.fileCommand(commands, player);
                    }
                }
            }
        }
    }
