package me.cuchufleto.firearmor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin implements CommandExecutor{

    ConsoleCommandSender console = getServer().getConsoleSender();

    @Override
    public void onEnable(){
        this.getServer().getPluginManager().registerEvents(new listener(), this);
        items.init();
        console.sendMessage("firearmor ENABLED");
    }

    @Override
    public void onDisable(){
        console.sendMessage("firearmor DISABLED");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        if(cmd.getName().equalsIgnoreCase("firearmor") && player.hasPermission("firearmor.use")){

            Inventory inv = player.getInventory();
            if(inv.firstEmpty() != -1)
                inv.addItem(items.exampleBoots);
            else
                player.getWorld().dropItemNaturally(player.getLocation(), items.exampleBoots);

            player.sendMessage("§4§oYou recieved a gift!");
            return true;
        }
        return false;
    }
}
