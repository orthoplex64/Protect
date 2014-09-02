package me.nentify.Protect.managers;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import me.nentify.Protect.Protect;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class WorldGuardManager {

    private Protect plugin;
    private WorldGuardPlugin worldGuardPlugin;

    public WorldGuardManager() {
        plugin = Protect.getInstance();
        worldGuardPlugin = (WorldGuardPlugin) plugin.getServer().getPluginManager().getPlugin("WorldGuard");
    }
    public WorldGuardPlugin getWorldGuardPlugin() {
        return worldGuardPlugin;
    }
}
