package live.PowerBlock.changeColor;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class changeColorExecutor implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if(label.equalsIgnoreCase("changecolor")){
            if(!(sender instanceof Player)){
                sender.sendMessage(ChatColor.RED + "You can't do that :I");
                return false;
            }
            Player player = (Player) sender;
            player.openInventory(changeColorGUI.createInv());
            return true;
        }
        return false;
    }
}
