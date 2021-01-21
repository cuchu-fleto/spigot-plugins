package me.cuchufleto.MobPoints;

import me.cuchufleto.MobPoints.sql.MySQL;
import me.cuchufleto.MobPoints.sql.SQLInterface;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public class Main extends JavaPlugin implements Listener {

    public MySQL sql;
    public SQLInterface sqlInterface;

    @Override
    public void onEnable(){
        this.sql = new MySQL();
        this.sqlInterface = new SQLInterface(this);
        this.getServer().getPluginManager().registerEvents(this, this);

        try {
            sql.connect();
        } catch (ClassNotFoundException | SQLException e){
            //e.printStackTrace();
            Bukkit.getLogger().info("[ERROR] MobPoints: MySQL Database Failed to Connect.");
        }
        if (sql.isConnected()){
            Bukkit.getLogger().info("[PASS] MobPoints: MySQL Database Connected!");
            sqlInterface.createTable();
        }

        this.getServer().getConsoleSender().sendMessage("MobPoints : ENABLED");
    }

    @Override
    public void onDisable(){
        sql.disconnect();
        this.getServer().getConsoleSender().sendMessage("MobPoints : DISABLED");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player && label.equalsIgnoreCase("mobpoints")){
            Player player = (Player) sender;
            //player.sendMessage(""+ sqlInterface.exists(player.getUniqueId())); debugging if player found
            player.sendMessage("§aYou have §d" + sqlInterface.getPoints(player.getUniqueId()) + "§a Mob Points.");
            return true;
        }

        return false;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        sqlInterface.createPlayer(player);
    }

    @EventHandler
    public void onMobKill(EntityDeathEvent event){
        if(event.getEntity().getKiller() instanceof Player){
            Player player = event.getEntity().getKiller();
            sqlInterface.addPoints(player.getUniqueId(), 1); //add 1 point
            player.sendMessage("§d+1 Mob Points");
        }
    }
}
