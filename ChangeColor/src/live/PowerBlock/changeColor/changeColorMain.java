package live.PowerBlock.changeColor;

import org.bukkit.plugin.java.JavaPlugin;

public class changeColorMain extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getServer().getConsoleSender().sendMessage("ChangeColor » Plugin Enabled");
        this.getCommand("changecolor").setExecutor(new changeColorExecutor());
        this.getServer().getPluginManager().registerEvents(new changeColorListener(), this);

    }

    @Override
    public void onDisable() {
        this.getServer().getConsoleSender().sendMessage("ChangeColor » Plugin Disabled");
    }
}
