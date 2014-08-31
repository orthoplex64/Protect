package me.nentify.Protect.managers;

import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.nentify.Protect.Protect;
import me.nentify.Protect.entries.ClaimEntry;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class ClaimManager {

    private Protect plugin;

    public ClaimManager() {
        this.plugin = Protect.getInstance();
    }

    public boolean addClaim(ClaimEntry newClaim) {
        if (newClaim.getMin().getWorld() != newClaim.getMax().getWorld()) {
            return false;
        }

        RegionManager mgr = plugin.getWorldGuard().getRegionManager(newClaim.getMin().getWorld());

        int worldHeight = plugin.getServer().getWorlds().get(0).getMaxHeight() -1;

        BlockVector min = new Vector(newClaim.getMin().getX(), 0.0D, newClaim.getMin().getZ()).toBlockVector();
        BlockVector max = new Vector(newClaim.getMax().getX(), worldHeight, newClaim.getMax().getZ()).toBlockVector();

        String id = newClaim.getOwnerName() + "-" + newClaim.getMin().getBlockX() + "-" + newClaim.getMin().getBlockZ();

        ProtectedRegion region = new ProtectedCuboidRegion(id, min, max);
        region.getOwners().addPlayer(newClaim.getOwnerName());

        Player player = Bukkit.getServer().getPlayer(newClaim.getOwnerName());

        if (mgr.getApplicableRegions(region).size() != 0) {
            player.sendMessage("Overlaps " + "lel");
            plugin.getPlayerManager().getPlayerEntry(newClaim.getOwnerName()).setPreviousLocation(null);
            return false;
        }

        mgr.addRegion(region);

        plugin.getPlayerManager().getPlayerEntry(newClaim.getOwnerName()).setPreviousLocation(null);

        return true;
    }
}
