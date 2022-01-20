package me.hyperburger.revblocks.listeners;

import me.hyperburger.revblocks.RevBlocks;
import me.hyperburger.revblocks.user.UserHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class PlayerJoinListener implements Listener {

    private final RevBlocks plugin;
    private final UserHandler userHandler;

    public PlayerJoinListener(RevBlocks plugin, UserHandler userHandler){
        this.plugin = plugin;
        this.userHandler = userHandler;
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event){

        // Player & UUID
        final Player player = event.getPlayer();
        final UUID uuid = player.getUniqueId();

            // Check to see if the player exists in the HashMap, if not register.
            if (!userHandler.exists(uuid)) {
                userHandler.register(uuid, player.getName(), 1); // Default block value of 1.
            }
        }
    }
