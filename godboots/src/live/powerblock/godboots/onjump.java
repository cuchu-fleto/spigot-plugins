package live.powerblock.godboots;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class onjump implements Listener {

    @EventHandler
    public void onJump(PlayerMoveEvent event){
        Player player = (Player) event.getPlayer();

        if(player.getInventory().getBoots() != null) //player has boots
            if(player.getInventory().getBoots().getItemMeta().getDisplayName().contains("GOD BOOTS")) //boots are named "GOD BOOTS"
                if(player.getInventory().getBoots().getItemMeta().hasLore()) //boots have lore (which players cant add to an item)
                    if(event.getFrom().getY() < event.getTo().getY() && //player jumped
                            player.getLocation().subtract(0,1,0).getBlock().getType() != Material.AIR && //block below them is not air
                            player.getLocation().getBlock().getType() != Material.WATER){  //they're not in water
                        player.setVelocity(player.getLocation().getDirection().multiply(2).setY(2));
                    }

    }
}