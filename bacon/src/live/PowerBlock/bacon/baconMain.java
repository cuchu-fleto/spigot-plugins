package live.PowerBlock.bacon;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class baconMain extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage("\"why did you do this to me\" -bacon");
        getServer().getPluginManager().registerEvents(this,this);
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("\"i can finally rest\" -bacon");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("\"you cant do that lol\" -bacon");
        }
        Player player = (Player) sender;
        if(label.equalsIgnoreCase("bacon")){
            //give bacon if player has space
            if(player.getInventory().firstEmpty() == -1){
                sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "\"you dont have any space\" -bacon");
                return false;
            }
            player.getInventory().addItem(getBacon());
            player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "\"you've been blessed\" -bacon");
            return true;
        }
        return false;
    }

    @EventHandler
    public void onEatBacon(PlayerItemConsumeEvent event){
        ItemStack item = event.getItem();
        if(event.getItem().getType().equals(Material.ROTTEN_FLESH)) //is rotten flesh
            if(item.getItemMeta().hasLore()){ //it's our rotten flesh (has lore)
                Player player = event.getPlayer();
                player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "\"hope i was tasty\" -bacon");
                player.removePotionEffect(PotionEffectType.getByName("HUNGER"));
                player.setHealth(20.0);
                player.addPotionEffect(getPotionEffect(PotionEffectType.SPEED, 1));
                player.addPotionEffect(getPotionEffect(PotionEffectType.SATURATION, 255));
                player.addPotionEffect(getPotionEffect(PotionEffectType.NIGHT_VISION, 255));
                player.addPotionEffect(getPotionEffect(PotionEffectType.ABSORPTION, 9));
            }
    }

    @EventHandler
    public void onShitTalk(AsyncPlayerChatEvent event){
        String[] keywords = {"shit","stupid","bad","trash","garbage","shitty","horrible", "terrible", "aravind"};

        String message = event.getMessage().toLowerCase();
        if(message.contains("bacon")){
            for(int i = 0; i < keywords.length; i++){
                if(message.contains(keywords[i])){
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "\"i know where you live :l\" -bacon");
                    return;
                }
            }
        }
    }

    public ItemStack getBacon(){
        ItemStack item = new ItemStack(Material.ROTTEN_FLESH);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&f&l&o&k|&4&l&oBACON&f&l&o&k|"));
        List<String> lore = new ArrayList <String>();
        lore.add("");
        lore.add(ChatColor.translateAlternateColorCodes('&', "&4&l&o&k&mAfdjfskEEgSHJHJHSsjhgGgaEUh"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&4&l&o&k&mFFETGjkebJKeUEUEUkksbsjklAE"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&4&l&o&k&mfsDDDGHjskfVVkejdgeiuvskEEs"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&4&l&o&k&mdggfsdlkhalKKDLKhdksdlflflVx"));

        meta.addEnchant(Enchantment.KNOCKBACK, 2, true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public PotionEffect getPotionEffect(PotionEffectType type, int amp){
        return (new PotionEffect(type, 12000, amp, false, false));
    }
}
