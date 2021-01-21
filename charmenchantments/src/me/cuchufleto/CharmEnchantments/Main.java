package me.cuchufleto.CharmEnchantments;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable(){
        CustomEnchantments.register(CustomEnchantments.EXCAVATION);
        CustomEnchantments.register(CustomEnchantments.SUMMONING);
        CustomEnchantments.register(CustomEnchantments.CREATION);
        CustomEnchantments.register(CustomEnchantments.DECEPTION);
        this.getServer().getPluginManager().registerEvents(new EventListener(), this);

        this.getServer().getConsoleSender().sendMessage("CharmEnchantments: ENABLED");


    }

    @Override
    public void onDisable(){
        getServer().getConsoleSender().sendMessage("CharmEnchantments: DISABLED");
    }


    //temp
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(label.equalsIgnoreCase("excavation")){
            ItemStack item = new ItemStack(Material.DIAMOND_SHOVEL);
            item.addUnsafeEnchantment(CustomEnchantments.EXCAVATION, 1);
            Player p = (Player) sender;
            p.getInventory().addItem(item);
            return true;
        }
        if(label.equalsIgnoreCase("summoning")){
            ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
            item.addUnsafeEnchantment(CustomEnchantments.SUMMONING, 1);
            Player p = (Player) sender;
            p.getInventory().addItem(item);
            return true;
        }
        if(label.equalsIgnoreCase("deception")){
            ItemStack item = new ItemStack(Material.DIAMOND_HOE);
            item.addUnsafeEnchantment(CustomEnchantments.DECEPTION, 1);
            Player p = (Player) sender;
            p.getInventory().addItem(item);
            return true;
        }
        return false;
    }
}
