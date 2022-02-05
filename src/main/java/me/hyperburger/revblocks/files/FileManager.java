package me.hyperburger.revblocks.files;

import me.hyperburger.revblocks.RevBlocks;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private File file;
    private FileConfiguration fileConfiguration;
    private final RevBlocks plugin;

    public FileManager(RevBlocks plugin, FileConfiguration fileConfiguration, File file){
        this.plugin = plugin;
        this.file = file;
        this.fileConfiguration = fileConfiguration;
    }

    public void setupFile(){
        file = new File(plugin.getDataFolder() + "/blockRewards.yml");
        if (!file.exists()){
            System.out.println("Couldn't find blockRewards.yml! Generating a new one.");
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
        if (!fileConfiguration.contains("Rewards")) {
            List<String> commandList = new ArrayList<>();
            commandList.add("[console] broadcast hello from console!");
            commandList.add("[player] spawn");

            fileConfiguration.createSection("Rewards");
            fileConfiguration.createSection("Rewards.reward1");
            fileConfiguration.createSection("Rewards.reward1.blocksNeeded");
            fileConfiguration.createSection("Rewards.reward1.every");
            fileConfiguration.createSection("Rewards.reward1.commands");

            fileConfiguration.set("Rewards.reward1.blocksNeeded", 100);
            fileConfiguration.set("Rewards.reward1.every", 0);
            fileConfiguration.set("Rewards.reward1.commands", commandList);

            saveFile();
        }
    }
}
