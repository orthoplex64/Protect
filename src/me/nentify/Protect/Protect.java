package me.nentify.Protect;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Protect extends JavaPlugin {
    private static Protect instance;
    private static Logger logger = Logger.getLogger("Minecraft");
    private PlayerManager playerManager;
    private PlayerListener playerListener;

    Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");

    public DataStore dataStore;

    public static Protect getInstance() {
        return instance;
    }

    public static Logger getLog() {
        return logger;
    }

    public static void log(String string) {
        logger.log(Level.INFO, string);
    }

    public void onEnable() {
        instance = this;

        log("Protection loaded");

        playerManager = new PlayerManager();

        playerListener = new PlayerListener();

        registerEvents();

        if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
            log("Please install WorldGuard");
        }
    }

    public void registerEvents() {
        getServer().getPluginManager().registerEvents(playerListener, this);
    }

    public void onDisable() {
        // Empty
    }

    public PlayerManager playerManager() {
        return playerManager;
    }

    public PlayerListener getPlayerListener() {
        return playerListener;
    }
}



/* IGNOREEEE
        if (heldItem.getType() == FEATHER) {
            player.sendMessage("Holding feather");
            if (event.getClickedBlock().getType() != Material.AIR) {
                if (x1 == 0) {
                    x1 = event.getClickedBlock().getX();
                    z1 = event.getClickedBlock().getZ();
                    player.sendMessage("pos 1 set");
                } else if (x2 == 0) {
                    x2 = event.getClickedBlock().getX();
                    z2 = event.getClickedBlock().getZ();
                    player.sendMessage("pos 2 set");

                    int worldHeight = world.getMaxHeight() - 1;
                    BlockVector min = new Vector(x1, 0.0D, z1).toBlockVector();
                    BlockVector max = new Vector(x2, worldHeight, z2).toBlockVector();

                    String xAsStr = Double.toString(x1);
                    String zAsStr = Double.toString(z1);

                    String id = xAsStr.substring(0, xAsStr.indexOf(".")) + "x" + zAsStr.substring(0, zAsStr.indexOf("."));

                    ProtectedRegion region = new ProtectedCuboidRegion(id, min, max);
                    region.getOwners().addPlayer(player.getName());
                    regionManager.addRegion(region);
                }
            }
        }*/