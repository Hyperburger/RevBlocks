package me.hyperburger.revblocks.placeholders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.hyperburger.revblocks.user.User;
import me.hyperburger.revblocks.user.UserHandler;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class BlockPlaceHolder extends PlaceholderExpansion {

    private final UserHandler userHandler;
    private int total = 0;
    public BlockPlaceHolder(UserHandler userHandler){
        this.userHandler = userHandler;
    }

    @Override
    public boolean persist(){
        return true;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "revblocks";
    }

    @Override
    public boolean canRegister(){
        return true;
    }


    @Override
    public @NotNull String getAuthor() {
        return "HyperBurger";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.1";
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        int globalBlockCount = 0;

        if (player == null) System.out.println("PLAYER IS NULl");

            // %revblocks_blocks%
            if (params.equalsIgnoreCase("blocks")) {
                return String.valueOf(userHandler.findUser(player.getUniqueId()).getBlockCount());
            }


            // %revblocks_global%
            if (params.equalsIgnoreCase("global")){
                for (User users : userHandler.getUsersMap().values()){
                    int blockCount = users.getBlockCount();
                    globalBlockCount = globalBlockCount + blockCount;
                }
                total = globalBlockCount;
                return String.valueOf(total);
            }
        return "Placeholder problem";
    }
}
