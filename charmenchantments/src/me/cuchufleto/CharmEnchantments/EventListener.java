package me.cuchufleto.CharmEnchantments;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;
import java.util.Set;

public class EventListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();
        if(itemInHand == null || !(itemInHand.hasItemMeta())) return;

        if(itemInHand.getItemMeta().hasEnchant(CustomEnchantments.EXCAVATION)){
            Block targetBlock = event.getPlayer().getTargetBlock((Set<Material>) null, 10); //the number here is the block range, by default 10 for performance and other reasons
            targetBlock.breakNaturally(); //break target block
        }

        if(itemInHand.getItemMeta().hasEnchant(CustomEnchantments.SUMMONING)){
            EntityType[] entities = {EntityType.CREEPER, EntityType.DROWNED, EntityType.HUSK, EntityType.ZOMBIE, EntityType.SKELETON}; //entities to spawn
            Block targetBlock = event.getPlayer().getTargetBlock((Set<Material>) null, 10); //the number here is the block range, by default 10 for performance and other reasons
            event.getPlayer().getWorld().spawnEntity(targetBlock.getLocation().add(0, 1, 0), entities[new Random().nextInt(entities.length)]); //pick random entity to spawn
        }

        if(itemInHand.getItemMeta().hasEnchant(CustomEnchantments.DECEPTION)){
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100, 1)); //give player 5 second invis
            event.getPlayer().teleport(event.getPlayer().getTargetBlock((Set<Material>) null, 50).getLocation().add(0, 1, 0)); //tp player to 1 above target block (50 range)
        }

        if(itemInHand.getItemMeta().hasEnchant(CustomEnchantments.CREATION)){

        }
    }
}
