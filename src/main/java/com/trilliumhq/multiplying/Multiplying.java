package com.trilliumhq.multiplying;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.cacheddata.CachedPermissionData;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.query.QueryOptions;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Iterator;
import java.util.Objects;

public final class Multiplying extends JavaPlugin implements Listener {

    public Multiplying() {
    }

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onXPPickup(PlayerExpChangeEvent event) {
        Player p = event.getPlayer();
//        p.sendMessage(String.valueOf(metaData.getMetaValue("exp", Integer::parseInt).orElse(1)));
        if(Objects.requireNonNull(event.getSource()).getType().equals(EntityType.EXPERIENCE_ORB)){
            event.setAmount(event.getAmount() * LuckPermsProvider.get().getPlayerAdapter(Player.class).getMetaData(p).getMetaValue("exp", Integer::parseInt).orElse(1));
        }
    }
    @Override
    public void onDisable() {
        this.getServer().getPluginManager().disablePlugin(this);
    }

}
