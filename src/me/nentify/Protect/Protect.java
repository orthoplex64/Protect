package me.nentify.Protect;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Protect extends JavaPlugin {

    Logger log;

    Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");

    public static Protect instance;

    public DataStore dataStore;

    @Override
    public void onEnable() {
        log = this.getLogger();

        if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
            log.info("Please install WorldGuard");
        }

        new BlockEventHandler(this);
        new PlayerEventHandler(this);

        log.info("Protect enabled");
    }

    @Override
    public void onDisable() {
        log.info("Protect disabled");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("claim")) {
        }
        return true;
    }
}
