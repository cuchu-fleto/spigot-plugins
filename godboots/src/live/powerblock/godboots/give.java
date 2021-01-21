package live.powerblock.godboots;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class give implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("godboots")){
            if(!(sender instanceof Player)){
                sender.sendMessage("you can't do that :l");
                return false;
            }
            Player player = (Player) sender;

            if(!(player.getInventory().getBoots() == null)) {
                //player has boots
                if (player.getInventory().firstEmpty() == -1) {
                    //player has no inv space
                    player.getWorld().dropItemNaturally(player.getLocation(), godbootsitem.godBoots());
                    player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "The gods have smiled upon you. Look around.");
                    return true;
                }
                //player has inv space
                player.getInventory().addItem(godbootsitem.godBoots());
                player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "The gods have smiled upon you.");
                return true;
            }
            //player has no boots
            player.getInventory().setBoots(godbootsitem.godBoots());
            player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "The gods have smiled upon you.");
            return true;
        }
        return false;
    }
}
