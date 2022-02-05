package me.hyperburger.revblocks.utilis;

import me.hyperburger.revblocks.RevBlocks;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

public class Updater {

    private final RevBlocks plugin;
    private final int resourceId;

    public Updater(RevBlocks plugin, int resourceId){
        this.plugin = plugin;
        this.resourceId = resourceId;

    }

    public void getLatestVersion(Consumer<String> consumer){
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + resourceId).openStream();
                Scanner scanner = new Scanner(inputStream);

                if (scanner.hasNext()){
                    consumer.accept(scanner.next());
                }
            } catch (IOException e){
                plugin.getLogger().info("[RevBlocks] Update Checker is broken! Please report this. " +  e.getMessage());

            }
        });
    }
}
