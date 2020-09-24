package live.PowerBlock.NightVision;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.StringEscapeUtils;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NightVision extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getServer().getConsoleSender().sendMessage(this.getConfig().getString("enable-message"));
        this.saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        this.getServer().getConsoleSender().sendMessage(this.getConfig().getString("disable-message"));
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("nightvision") || label.equalsIgnoreCase("nv")) { // "/nightvision" or "/nv"


            /////////////////////////TOGGLE/////////////////////////

            if (args.length == 0) { //no args are passed (toggle mode)
                if (!(sender instanceof Player)) { //sender is not player
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("non-player-entity")));
                    return false;
                }
                Player player = (Player) sender;
                if (player.hasPermission("nightvision.toggle")) { //player has perms to toggle
                    if ((player.getPotionEffect(PotionEffectType.NIGHT_VISION) == null)) { //check if player has night vision
                        //turn on nightvision
                        setNightVision(player, true);
                        return true;
                    }
                    //turn off nightvision
                    setNightVision(player, false);
                    return true;
                }
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("no-permission")));
                return false;
            }


            /////////////////////////RELOAD/////////////////////////

            String arg0;
            try {
                arg0 = args[0];
            } catch (Exception e) {
                sender.sendMessage(ChatColor.RED + "Usage: /nightvision [toggle]");
                return false;
            }
            if (arg0.equalsIgnoreCase("reload")) {
                if (sender.hasPermission("nightvision.reload")) {
                    this.reloadConfig();
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatColor.translateAlternateColorCodes('&', StringEscapeUtils.unescapeJava(this.getConfig().getString("prefix")))) + "Configuration Reloaded Successfully");
                    return true;
                }
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("no-permission")));
            }
        }
        return false;
    }

    public void setNightVision(Player player, Boolean setNightVision) {
        //turn on night vision
        if (setNightVision) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999, 255, false, false));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatColor.translateAlternateColorCodes('&', StringEscapeUtils.unescapeJava(this.getConfig().getString("prefix")))) + "Enabled NightVision for " + "" + ChatColor.ITALIC + "" + ChatColor.GREEN + "" + player.getName());
        }
        else {
            //turn off night vision
            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatColor.translateAlternateColorCodes('&', StringEscapeUtils.unescapeJava(this.getConfig().getString("prefix")))) + "Disabled NightVision for " + "" + ChatColor.ITALIC + "" + ChatColor.RED + "" + player.getName());
        }
    }
}
