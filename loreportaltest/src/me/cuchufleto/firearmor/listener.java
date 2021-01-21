package me.cuchufleto.firearmor;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class listener implements Listener {
    @EventHandler
    public static void onEntityDamagedByPlayer(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player && ((Player) event.getDamager()).getInventory().getBoots().equals(items.exampleBoots)) //damager is player and is wearing exampleBoots
            event.getEntity().setFireTicks(80); //80 fire ticks, same as Fire Aspect I
    }
}
