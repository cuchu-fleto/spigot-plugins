package me.cuchufleto.MobPoints.sql;

import me.cuchufleto.MobPoints.Main;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SQLInterface {

    private Main plugin;

    public SQLInterface(Main plugin){
        this.plugin = plugin;
        plugin.sql.getConnection();
    }

    public void createTable(){
        try{
            PreparedStatement ps = plugin.sql.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS mobpoints " +
                    "(name VARCHAR(100),uuid VARCHAR(100),points INT(100),PRIMARY KEY (name))"); //make table if it doesnt exist + add appropriate fields
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void createPlayer(Player player){
        try {
            UUID uuid = player.getUniqueId();
            if(!exists(uuid)){ //if user doesn't exist, add them to the table
                PreparedStatement ps2 = plugin.sql.getConnection().prepareStatement("INSERT IGNORE INTO mobpoints (name,uuid,points) VALUES (?,?,0)"); //insert name and uuid
                ps2.setString(1, player.getDisplayName());
                ps2.setString(2, uuid.toString());
                ps2.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean exists(UUID uuid){
        try{
            PreparedStatement ps = plugin.sql.getConnection().prepareStatement("SELECT * FROM mobpoints WHERE UUID=?");
            ps.setString(1, uuid.toString());

            ResultSet results = ps.executeQuery();
            if(results.next()) return true; //return if player is found in table

        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }



    public void addPoints(UUID uuid, int points){
        try{
            PreparedStatement ps = plugin.sql.getConnection().prepareStatement("UPDATE mobpoints SET points=? WHERE uuid=?");
            ps.setInt(1, getPoints(uuid) + points);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int getPoints(UUID uuid){
        try{
            PreparedStatement ps = plugin.sql.getConnection().prepareStatement("SELECT points FROM mobpoints WHERE uuid=?");
            ps.setString(1, uuid.toString());
            ResultSet results = ps.executeQuery();

            int points = 0;
            if(results.next()){
                points = results.getInt("points");
                return points;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }
}
