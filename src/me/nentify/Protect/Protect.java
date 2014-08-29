package me.nentify.Protect;

import com.sk89q.worldguard.bukkit.RegionContainer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.internal.PermissionModel;
import me.nentify.Protect.listeners.PlayerListener;
import me.nentify.Protect.managers.ClaimManager;
import me.nentify.Protect.managers.CommandManager;
import me.nentify.Protect.managers.PlayerManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.security.Permission;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Protect extends JavaPlugin {
    private static Protect instance;
    private static Logger logger = Logger.getLogger("Minecraft");
    private CommandManager commandManager;
    private ClaimManager claimManager;
    private PlayerManager playerManager;
    private PlayerListener playerListener;

    public static net.milkbowl.vault.permission.Permission permission = null;
    private static Economy economy = null;

    private WorldGuardPlugin worldGuard;

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
        worldGuard = ((WorldGuardPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldGuard"));

        log("Protection loaded");

        commandManager = new CommandManager();
        claimManager = new ClaimManager();
        playerManager = new PlayerManager();

        playerListener = new PlayerListener();

        setupEconomy();
        setupPermissions();

        registerEvents();
        registerCommands();
    }

    public void registerEvents() {
        getServer().getPluginManager().registerEvents(playerListener, this);
    }

    public void registerCommands() {
        getCommand("claim").setExecutor(getCommandManager());
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<net.milkbowl.vault.permission.Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }

    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }
        return (economy != null);
    }

    public void onDisable() {
        // Empty
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public ClaimManager getClaimManager() {
        return claimManager;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public PlayerListener getPlayerListener() {
        return playerListener;
    }

    public WorldGuardPlugin getWorldGuard() {
        return worldGuard;
    }
}