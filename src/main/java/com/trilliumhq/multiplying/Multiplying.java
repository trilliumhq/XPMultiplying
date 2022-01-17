package com.trilliumhq.multiplying;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.java.JavaPlugin;

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
        String permissionMultipler = p.getEffectivePermissions().stream().map(PermissionAttachmentInfo::getPermission).filter(permission -> permission.startsWith("expmultiplier.amount.")).findFirst().get();
        event.setAmount(event.getAmount() * Integer.parseInt(permissionMultipler.substring(permissionMultipler.lastIndexOf(".") + 1)));
    }
    @Override
    public void onDisable() {
        this.getServer().getPluginManager().disablePlugin(this);
    }

}
