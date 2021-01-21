package live.powerblock.godboots;

import org.bukkit.plugin.java.JavaPlugin;

public class godboots extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getServer().getConsoleSender().sendMessage("godboots » Plugin Enabled");
        this.getCommand("godboots").setExecutor(new give());
        this.getServer().getPluginManager().registerEvents(new onjump(),this);
        this.getServer().getPluginManager().registerEvents(new onfall(),this);
    }
    @Override
    public void onDisable() {
        this.getServer().getConsoleSender().sendMessage("godboots » Plugin Disabled");
    }

}
