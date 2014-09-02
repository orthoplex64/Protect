package me.nentify.Protect;

import me.nentify.Protect.listeners.PlayerListener;
import me.nentify.Protect.managers.ClaimManager;
import me.nentify.Protect.managers.CommandManager;
import me.nentify.Protect.managers.PlayerManager;
import me.nentify.Protect.managers.WorldGuardManager;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Protect extends JavaPlugin {
    private static Protect instance;
    private static Logger logger = Logger.getLogger("Minecraft");
    private CommandManager commandManager;
    private ClaimManager claimManager;
    private PlayerManager playerManager;
    private WorldGuardManager worldGuardManager;
    private PlayerListener playerListener;
    
    public static Permission permission;
    private static Economy economy;
    
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
        
        log("Protection enabled");
        
        commandManager = new CommandManager();
        claimManager = new ClaimManager();
        playerManager = new PlayerManager();
        worldGuardManager = new WorldGuardManager();
        
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
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }
    
    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }
        return (economy != null);
    }
    
    public void onDisable() {
        log("Protecion disabled");
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
    public WorldGuardManager getWorldGuardManager() {
        return worldGuardManager;
    }
    
    public PlayerListener getPlayerListener() {
        return playerListener;
    }
}