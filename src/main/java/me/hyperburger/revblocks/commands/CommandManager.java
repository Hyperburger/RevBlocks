package me.hyperburger.revblocks.commands;

import me.hyperburger.revblocks.RevBlocks;
import me.hyperburger.revblocks.commands.subcommands.*;
import me.hyperburger.revblocks.utilis.Utilis;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor {

    private final ArrayList<SubCommand> subCommands = new ArrayList<>();
    private final RevBlocks revBlocks;

    public CommandManager(RevBlocks revBlocks){
        subCommands.add(new Reload(revBlocks));
        subCommands.add(new Check(revBlocks.getUserHandler()));
        subCommands.add(new Set(revBlocks.getUserHandler()));
        subCommands.add(new Add(revBlocks.getUserHandler()));
        subCommands.add(new Remove(revBlocks.getUserHandler()));

        this.revBlocks = revBlocks;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return true;
            Player player = (Player) sender;

            if (!player.hasPermission("revblocks.view")) return true;

            if (args.length > 0){
                for (SubCommand subCommand : subCommands){
                    if (args[0].equalsIgnoreCase(subCommand.getName())) {
                        subCommand.perform(player, args, revBlocks);
                    }
                }
            } else {
                player.sendMessage(Utilis.hexMessage("\n    &f&m--------&e&l  RevBlocks &fv&6&l1.0  &f&m--------\n"));
                for (SubCommand subCommand : subCommands){
                    player.sendMessage(Utilis.hexMessage(" &8[&6&l*&8] &f" + subCommand.getSyntax() + " &6- &7" + subCommand.getDescription()));
                }
                player.sendMessage(Utilis.hexMessage("\n &8[&6&l?&8] &7Author: HyperBurger"));
                player.sendMessage(" ");
                player.sendMessage(Utilis.hexMessage("&8[&3&l*&8] &7&oSupports 1.8 - 1.18\n"));
                player.sendMessage(" ");
                player.sendMessage(Utilis.hexMessage(" &8[&6&l?&8] &7&o((Please rate this plugin. ))"));
                player.sendMessage(Utilis.hexMessage("\n    &f&m----------&e&l  &e&m----------&f  &f&m----------\n"));

            }

        return true;
    }
}
