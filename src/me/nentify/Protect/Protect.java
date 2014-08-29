package me.nentify.Protect;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import me.nentify.Protect.listeners.PlayerListener;
import me.nentify.Protect.managers.ClaimManager;
import me.nentify.Protect.managers.PlayerManager;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Protect extends JavaPlugin {
    private static Protect instance;
    private static Logger logger = Logger.getLogger("Minecraft");
    private ClaimManager claimManager;
    private PlayerManager playerManager;
    private PlayerListener playerListener;

    Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");

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

        claimManager = new ClaimManager();
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

    public ClaimManager claimManager() {
        return claimManager;
    }

    public PlayerManager playerManager() {
        return playerManager;
    }

    public PlayerListener getPlayerListener() {
        return playerListener;
    }
}