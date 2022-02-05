package me.hyperburger.revblocks.commands.subcommands;

import me.hyperburger.revblocks.user.User;
import me.hyperburger.revblocks.user.UserHandler;
import me.hyperburger.revblocks.utilis.Utilis;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Remove extends SubCommand{

    private final UserHandler userHandler;

    public Remove(UserHandler userHandler){
        this.userHandler = userHandler;
    }

    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public String getDescription() {
        return "removes block value from the player.";
    }

    @Override
    public String getSyntax() {
        return "/revblocks remove <player> <blocks>";
    }

    @Override
    public String permission() {
        return "revblocks.remove";
    }

    @Override
    public void perform(Player player, String[] args, Plugin plugin) {

        if (args.length > 2){
            Player target = Bukkit.getPlayer(args[1]);

            if (target != null){
                User user = userHandler.findUser(target.getUniqueId());
                user.removeBlockCount(Integer.parseInt(args[2]));

                player.sendMessage(Utilis.hexMessage(
                        "&7[&eRevBlocks&7] " + user.getName() + " &fblock count is now &e" + user.getBlockCount()));
            }
        } else {
            player.sendMessage(Utilis.hexMessage("&c/revblocks remove <player> <blocks>"));
        }
    }
}
