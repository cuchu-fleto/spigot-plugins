package live.powerblock.helloworld;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class helloworld extends JavaPlugin {

    @Override
    public void onEnable() {}
    @Override
    public void onDisable() {}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("helloworld")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if(player.hasPermission("helloworld.use")) {
                    player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "hi there :)");
                    return true;
                }
                else{
                    player.sendMessage(ChatColor.RED + "You do not have the permissions for that.");
                    return true;
                }
            } else {
                //console
                sender.sendMessage("hi there console :)");
                return true;
            }
        }
        return false;
    }
}
