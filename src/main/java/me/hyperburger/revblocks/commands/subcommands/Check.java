package me.hyperburger.revblocks.commands.subcommands;

import me.hyperburger.revblocks.user.User;
import me.hyperburger.revblocks.user.UserHandler;
import me.hyperburger.revblocks.utilis.Utilis;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Check extends SubCommand{

    private final UserHandler userHandler;
    public Check(UserHandler userHandler){
        this.userHandler = userHandler;
    }

    @Override
    public String getName() {
        return "check";
    }

    @Override
    public String getDescription() {
        return "View a player's block count.";
    }

    @Override
    public String getSyntax() {
        return "/revblocks check <player>";
    }

    @Override
    public void perform(Player player, String[] args, Plugin plugin) {
        if (args.length > 1){
            Player target = Bukkit.getPlayer(args[1]);

            if (target != null) {
                User user = userHandler.findUser(target.getUniqueId());
                player.sendMessage(
                        Utilis.hexMessage(
                                "&7[&eRevBlocks&7] &fThe player &e"
                                        + user.getName()
                                        + "&f has &e" + user.getBlockCount()
                                        + "&f blocks mined."));

            }
        } else if (args.length == 1){
            player.sendMessage(Utilis.hexMessage("&cWrong Usage: /revblocks check <player> "));
        }
    }
}
