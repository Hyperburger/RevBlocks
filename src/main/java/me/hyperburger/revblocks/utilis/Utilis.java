package me.hyperburger.revblocks.utilis;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Utilis {

    static public final String WITH_DELIMITER = "((?<=%1$s)|(?=%1$s))";

    public static String color(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    /**
     * Executable by players or console.
     * [player] | [console]
     * @param command The command line that will be executed.
     * @param player The player that the command will be executed from.
     * Supports placeholders. %player% - player's name.
     */
    public static void fileCommand (String command, Player player){
        if (command.startsWith("[player]")){
            Bukkit.dispatchCommand(player, command.replace("[player] ", "").replace("%player%", player.getName()));
        } else if (command.startsWith("[console]")){
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("[console] ", "").replace("%player%", player.getName()));

        }
    }

    /**
     * @param text The string of text to apply color/effects to
     * @return Returns a string of text with color/effects applied
     */
    public static String hexMessage(String text){

        String[] texts = text.split(String.format(WITH_DELIMITER, "&"));

        StringBuilder finalText = new StringBuilder();

        for (int i = 0; i < texts.length; i++){
            if (texts[i].equalsIgnoreCase("&")){
                //get the next string
                i++;
                if (texts[i].charAt(0) == '#'){
                    finalText.append(net.md_5.bungee.api.ChatColor.of(texts[i].substring(0, 7)) + texts[i].substring(7));
                }else{
                    finalText.append(ChatColor.translateAlternateColorCodes('&', "&" + texts[i]));
                }
            }else{
                finalText.append(texts[i]);
            }
        }

        return finalText.toString();
    }
}
