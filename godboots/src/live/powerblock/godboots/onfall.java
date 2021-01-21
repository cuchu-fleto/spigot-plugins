package live.powerblock.godboots;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class onfall implements Listener {

    @EventHandler
    public void onFall(EntityDamageEvent event){
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if(event.getCause() == EntityDamageEvent.DamageCause.FALL) //player taking damage from fall
                if(player.getInventory().getBoots() != null) //player has boots
                    if(player.getInventory().getBoots().getItemMeta().getDisplayName().contains("GOD BOOTS")) //boots are named "GOD BOOTS"
                        if(player.getInventory().getBoots().getItemMeta().hasLore()) { //boots have lore (which players cant add to an item)
                            event.setCancelled(true);

                        }
        }
    }
}