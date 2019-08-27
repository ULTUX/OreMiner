package pl.bullcube.ultux.veinminer;



import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{

    static Plugin pluginInstance = null;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("blockbreak")){
            if (sender instanceof Player){
                Player player = (Player) sender;
                player.getWorld().spawnParticle(Particle.BLOCK_CRACK, player.getLocation(), 500, 0.25, 0.25, 0.25, 0.25, Bukkit.createBlockData("stone"));
                player.getWorld().playSound(player.getLocation(), Sound.BLOCK_STONE_BREAK, 1, 1);
                player.sendMessage(Events.pickNumber(0, 32)+"");
                return true;
            }
        }
        return false;
    }

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
