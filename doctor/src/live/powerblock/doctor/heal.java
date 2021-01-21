package live.powerblock.doctor;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class heal implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("doctor")) {

            //is the executing entity allowed to do this?
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "You can't do that :l");
                return false;
            }
            Player player = (Player) sender;
            if(!player.hasPermission("doctor.use")) {
                player.sendMessage(ChatColor.RED + "You don't have permission to do that.");
                return false;
            }
            if(args.length == 0) {
                TextComponent message = new TextComponent("Click here to be healed");
                message.setColor(ChatColor.GOLD);
                message.setBold(true);
                message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/doctor healme"));
                message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new Text(ChatColor.AQUA + "" + ChatColor.ITALIC + "yep, right there")));
                return true;
            }
            if (args[0].equalsIgnoreCase("healme")){
                player.setHealth(20.0);
                player.sendMessage(ChatColor.GREEN + "You've been healed :)");
                return true;
            }
            player.sendMessage(ChatColor.RED + "Usage: /doctor");
        }
        return false;
    }
}