package me.hyperburger.revblocks.commands;

import me.hyperburger.revblocks.commands.subcommands.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TabComplete implements TabCompleter {

    private final CommandManager commandManager;
    public TabComplete(final CommandManager commandManager){
        this.commandManager = commandManager;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if (args.length == 1){
            // Create an ArrayList that contains the subcommands.
            List<String> subCommandNames = new ArrayList<>();

            // Loop and add the names to the list.
            for (SubCommand subCommand : commandManager.getSubCommands()){
                subCommandNames.add(subCommand.getName());
            }
            return subCommandNames; // Return names.

        } else if (args.length == 2) {
            // Create an ArrayList that contains the player names.
            List<String> playerNames = new ArrayList<>();

            // Loop through all online players and add their names to the list.
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                playerNames.add(onlinePlayer.getName());
            }
            return playerNames; // Return names.
        }
        return null;
    }
}
