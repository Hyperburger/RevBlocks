package me.hyperburger.revblocks.user;

import me.hyperburger.revblocks.RevBlocks;
import java.util.HashMap;
import java.util.UUID;

public class UserHandler {

    public HashMap<UUID, User> users = new HashMap<>();
    private final RevBlocks plugin;

    public UserHandler(RevBlocks plugin){
        this.plugin = plugin;}

    public void register(final UUID uuid, String name, final int blocks){
        users.put(uuid, new User(uuid, name, blocks));}

    public boolean exists(final UUID uuid){
        return users.containsKey(uuid);}

    public HashMap<UUID, User> getUsersMap(){
        return users;
    }

    public User findUser(UUID uuid){
        return users.get(uuid);}


}
