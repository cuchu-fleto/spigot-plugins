package me.cuchufleto.firearmor;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class items {

    public static ItemStack exampleBoots;

    public static void init(){
        ItemStack item = new ItemStack(Material.GOLDEN_BOOTS);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§6§lFire Boots");
        meta.addEnchant(Enchantment.FIRE_ASPECT, 1, false);
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§c§lSets entities you hit while wearing on §6§lfire§c§l!");
        meta.setLore(lore);

        item.setItemMeta(meta);
        exampleBoots = item;
    }
}
