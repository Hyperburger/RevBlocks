package me.hyperburger.revblocks.files;

import me.hyperburger.revblocks.RevBlocks;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileSettings {

    private File file;
    private FileConfiguration fileConfiguration;
    private final RevBlocks plugin;

    public FileSettings(RevBlocks plugin, FileConfiguration fileConfiguration, File file){
        this.plugin = plugin;
        this.file = file;
        this.fileConfiguration = fileConfiguration;
    }

    public void setupFile(){
        file = new File(plugin.getDataFolder() + "/settings.yml");
        if (!file.exists()){
            System.out.println("Couldn't find settings.yml! Generating a new one.");
            try {
                file.createNewFile();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public void saveFile(){
        try {
            fileConfiguration.save(file);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public FileConfiguration get(){
        return fileConfiguration;
    }

    public void reloadFile() {fileConfiguration = YamlConfiguration.loadConfiguration(file);}

    public void setDefaults() {
        if (!fileConfiguration.contains("Settings")) {
            List<String> blackList = new ArrayList<>();
            blackList.add("DIRT");
            blackList.add("GRASS");

            fileConfiguration.set("survival_mode_only", true);
            fileConfiguration.set("block_command_message", "&8[&6&l*&8] &7You have %blocks% broken blocks!");
            fileConfiguration.set("block_black_list", blackList);


            saveFile();
        }
    }
}
