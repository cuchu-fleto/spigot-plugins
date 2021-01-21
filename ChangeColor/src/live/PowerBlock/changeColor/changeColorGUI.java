package live.PowerBlock.changeColor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class changeColorGUI {
    public static Inventory createInv(){
        Inventory inv = Bukkit.createInventory(null, 9, ChatColor.BLACK + "" + ChatColor.BOLD + "Change Color");

        //create item (we'll add this item to the menu, change it, add it, etc.)
        ItemStack item = new ItemStack(Material.RED_CONCRETE);
        ItemMeta meta = item.getItemMeta();

        //set item lore (won't change)
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "Click to change your color!");
        meta.setLore(lore);

        //red
        meta.setDisplayName(ChatColor.RED + "RED");
        item.setItemMeta(meta);
        inv.setItem(0, item);

        //orange
        item.setType(Material.ORANGE_CONCRETE);
        meta.setDisplayName(ChatColor.GOLD + "ORANGE");
        item.setItemMeta(meta);
        inv.setItem(1, item);

        //yellow
        item.setType(Material.YELLOW_CONCRETE);
        meta.setDisplayName(ChatColor.YELLOW + "YELLOW");
        item.setItemMeta(meta);
        inv.setItem(2, item);

        //green
        item.setType(Material.LIME_CONCRETE);
        meta.setDisplayName(ChatColor.GREEN + "GREEN");
        item.setItemMeta(meta);
        inv.setItem(3, item);

        //blue
        item.setType(Material.CYAN_CONCRETE);
        meta.setDisplayName(ChatColor.BLUE + "BLUE");
        item.setItemMeta(meta);
        inv.setItem(4, item);

        //purple
        item.setType(Material.PURPLE_CONCRETE);
        meta.setDisplayName(ChatColor.DARK_PURPLE + "PURPLE");
        item.setItemMeta(meta);
        inv.setItem(5, item);

        //violet
        item.setType(Material.MAGENTA_CONCRETE);
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "PINK");
        item.setItemMeta(meta);
        inv.setItem(6, item);

        //close button
        item.setType(Material.BARRIER);
        meta.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Go Back");
        lore.clear();
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(8, item);

        return inv;
    }
}
