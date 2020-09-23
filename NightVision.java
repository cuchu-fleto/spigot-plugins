package live.PowerBlock.NightVision;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NightVision extends JavaPlugin {
    public boolean nightVisionEnabled = false;

    @Override
    public void onEnable() {
        this.getServer().getConsoleSender().sendMessage("NightVision » Plugin Enabled");
    }
    @Override
    public void onDisable() {
        this.getServer().getConsoleSender().sendMessage("NightVision » Plugin Disabled");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "You can't do that.");
            return false;
        }
        if(!(sender.hasPermission("nightvision.toggle"))){
            sender.sendMessage(ChatColor.RED + "You don't have the permissions to do that.");
        }

        Player player = (Player) sender;
        if(label.equalsIgnoreCase("nightvision") || label.equalsIgnoreCase("nv")){
            if(!nightVisionEnabled) {
                player.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "NightVision" + ChatColor.DARK_GRAY + "" + ChatColor.BOLD + " » " + ChatColor.GRAY + "Enabled NightVision for " + "" + ChatColor.ITALIC + "" + ChatColor.GREEN + "" + player.getName());
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999, 255, false, false));
                nightVisionEnabled = true;
                return true;
            }
            player.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "NightVision" + ChatColor.DARK_GRAY + "" + ChatColor.BOLD + " » " + ChatColor.GRAY + "Disabled NightVision for " + "" + ChatColor.ITALIC + "" + ChatColor.RED + "" + player.getName());
            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            nightVisionEnabled = false;
            return true;
        }
        return false;
    }
}
