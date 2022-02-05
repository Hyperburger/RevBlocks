package me.hyperburger.revblocks.listeners;

import me.hyperburger.revblocks.RevBlocks;
import me.hyperburger.revblocks.user.UserHandler;
import me.hyperburger.revblocks.utilis.Updater;
import me.hyperburger.revblocks.utilis.Utilis;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class PlayerJoinListener implements Listener {

    private final RevBlocks plugin;
    private final UserHandler userHandler;

    public PlayerJoinListener(RevBlocks plugin, UserHandler userHandler) {
        this.plugin = plugin;
        this.userHandler = userHandler;
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        // Player & UUID
        final Player player = event.getPlayer();
        final UUID uuid = player.getUniqueId();

        // Check to see if the player exists in the HashMap, if not register.
        if (!userHandler.exists(uuid)) {
            userHandler.register(uuid, player.getName(), 1); // Default block value of 1.
        }

        if (player.hasPermission("revblocks.view") || player.isOp()) {
            new Updater(plugin, 66139).getLatestVersion(ver -> {
                if (!plugin.getDescription().getVersion().equalsIgnoreCase(ver)) {
                    player.sendMessage(Utilis.color("        &8&m------ &8[&eRevBlocks&8] &8&m------ "));
                    player.sendMessage(Utilis.color("           &6&lOUTDATED VERSION: &f&n" + plugin.getDescription().getVersion()));
                    player.sendMessage(Utilis.color("\n  &6&lUPDATE AT&f:\n &7&ohttps://www.spigotmc.org/resources/authors/fishyhyper.643277/"));
                    player.sendMessage(Utilis.color("        &8&m------ ----------- &8&m------"));
                }
            });
        }
    }
}