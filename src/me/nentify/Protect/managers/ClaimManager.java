package me.nentify.Protect.managers;

import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.nentify.Protect.Protect;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;

public class ClaimManager {

    private Protect plugin;

    public ClaimManager() {
        this.plugin = Protect.getInstance();
    }

    public void addClaim(World world, Location loc1, Location loc2, String playerName) {
        RegionManager regionManager = plugin.getWorldGuard().getRegionManager(world);

        int worldHeight = plugin.getServer().getWorlds().get(0).getMaxHeight() -1;

        BlockVector min = new Vector(loc1.getX(), 0.0D, loc1.getZ()).toBlockVector();
        BlockVector max = new Vector(loc2.getX(), worldHeight, loc2.getZ()).toBlockVector();

        String id = playerName + "-" + loc1.getBlockX() + "-" + loc1.getBlockZ();

        ProtectedRegion region = new ProtectedCuboidRegion(id, min, max);
        region.getOwners().addPlayer(playerName);
        regionManager.addRegion(region);

        plugin.getPlayerManager().getPlayerEntry(playerName).setPreviousLocation(null);
    }
}
