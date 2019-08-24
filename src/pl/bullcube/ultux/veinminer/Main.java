package pl.bullcube.ultux.veinminer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{

    protected static Plugin pluginInstance = null;

    @Override
    public void onDisable() {
        Bukkit.getServer().getConsoleSender().sendMessage("[VeinMiner] Plugin loaded and enabled.");
    }

    @Override
    public void onEnable() {
        Bukkit.getServer().getConsoleSender().sendMessage("[VeinMiner] Plugin unloaded and disabled.");
        Bukkit.getPluginManager().registerEvents(new Events(), this);
        pluginInstance = this;
    }
}
