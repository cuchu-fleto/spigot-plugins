package live.powerblock.launch;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class launch extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "launch » Plugin Enabled");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "launch » Plugin Disabled");
    }


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        //test if command is "/launch" or "/fling"
        if (label.equalsIgnoreCase("launch") || label.equalsIgnoreCase("fling")) {
            //error and break if sender isn't a player
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "You can't do that :l");
                return false;
            }

            switch (args.length){
                //good arguments
                case 0:
                    launchPlayer((Player) sender, 10, 3);
                    return true;
                case 1:
                    if (isInt(args[0])) {
                        launchPlayer((Player) sender, Integer.parseInt(args[0]), 3);
                    }
                    return true;
                case 2:
                    if (isInt(args[0]) && isInt(args[1])) {
                        launchPlayer((Player) sender, Integer.parseInt(args[0]), Integer.parseInt(args[1]));
                    }
                    return true;
                //bad arguments
                default:
                    sender.sendMessage(ChatColor.RED + "Usage: /launch [multiplier] [height offset]");
                    return false;
            }
        }
        return false;
    }

    public void launchPlayer(Player player, int multiplier, int heightoffset){
        player.setVelocity(player.getLocation().getDirection().multiply(multiplier).setY(heightoffset));
    }

    //test if a passed argument is an int
    public boolean isInt(String num){
        try {
            Integer.parseInt(num);
        }
        catch (Exception exception) {
            return false;
        }
        return true;
    }
}
