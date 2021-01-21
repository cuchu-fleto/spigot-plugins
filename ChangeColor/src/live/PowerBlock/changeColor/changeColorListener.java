package live.PowerBlock.changeColor;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class changeColorListener implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(!event.getInventory().equals(changeColorGUI.createInv())) return;
        ItemStack currentItem = event.getCurrentItem();
        if(currentItem == null) return; //clicked on empty slot
        if(currentItem.getItemMeta().getDisplayName() == null) return; //item has no display name
        if(currentItem.getItemMeta() == null) return; //item has no meta (not one of our items)

        event.setCancelled(true); //stop player from picking up item

        Player player = (Player) event.getWhoClicked();

        switch (event.getSlot()) {
            case 0:
                updateArmor(player, Color.RED, ChatColor.RED);
                break;
            case 1:
                updateArmor(player, Color.ORANGE, ChatColor.GOLD);
                break;
            case 2:
                updateArmor(player, Color.YELLOW, ChatColor.YELLOW);
                break;
            case 3:
                updateArmor(player, Color.LIME, ChatColor.GREEN);
                break;
            case 4:
                updateArmor(player, Color.BLUE, ChatColor.BLUE);
                break;
            case 5:
                updateArmor(player, Color.PURPLE, ChatColor.DARK_PURPLE);
                break;
            case 6:
                updateArmor(player, Color.PURPLE, ChatColor.LIGHT_PURPLE);
                break;
            case 8:
                player.closeInventory();
                break;
            default:
                player.sendMessage(ChatColor.GRAY + "Please Select a Valid Slot!");
                break;
        }
    }

    public ItemStack[] changeColor(ItemStack[] a, Color color){
        for (ItemStack item : a){
            try {
                if (item.getType() == Material.LEATHER_HELMET || item.getType() == Material.LEATHER_CHESTPLATE || item.getType() == Material.LEATHER_LEGGINGS || item.getType() == Material.LEATHER_BOOTS) {
                    LeatherArmorMeta armorMeta = (LeatherArmorMeta) item.getItemMeta();
                    armorMeta.setColor(color);
                    item.setItemMeta(armorMeta);
                }
            }
            catch (Exception e){
                System.out.println(e);
            }
        }
        return a;
    }

    public void updateArmor(Player player, Color armorColor, ChatColor chatColor){
        ItemStack[] armor = player.getEquipment().getArmorContents();
        armor = changeColor(armor, armorColor);
        player.getEquipment().setArmorContents(armor);
        player.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "Color changed to " + chatColor + "" + ChatColor.BOLD + "" + ChatColor.ITALIC + "BLUE");
    }
}
