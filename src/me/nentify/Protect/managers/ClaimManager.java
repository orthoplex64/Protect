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

    public void addClaim(ClaimEntry newClaim) {
        if (newClaim.getMin().getWorld() != newClaim.getMax().getWorld()) {
            return;
        }

        RegionManager mgr = plugin.getWorldGuardManager().getWorldGuardPlugin().getRegionManager(newClaim.getMin().getWorld());

        int worldHeight = plugin.getServer().getWorlds().get(0).getMaxHeight() -1;

        BlockVector min = new Vector(newClaim.getMin().getX(), 0.0D, newClaim.getMin().getZ()).toBlockVector();
        BlockVector max = new Vector(newClaim.getMax().getX(), worldHeight, newClaim.getMax().getZ()).toBlockVector();

        String id = newClaim.getOwnerName() + "-" + newClaim.getMin().getBlockX() + "-" + newClaim.getMin().getBlockZ();

        ProtectedRegion region = new ProtectedCuboidRegion(id, min, max);
        region.getOwners().addPlayer(newClaim.getOwnerName());

        Player player = Bukkit.getServer().getPlayer(newClaim.getOwnerName());
        LocalPlayer localPlayer = plugin.getWorldGuardManager().getWorldGuardPlugin().wrapPlayer(player);

        if (mgr.getApplicableRegions(region).size() != 0) {
            player.sendMessage("Overlaps " + "lel");
            plugin.getPlayerManager().getPlayerEntry(newClaim.getOwnerName()).setPreviousLocation(null);
            return;
        }

        int currentRegionCount = mgr.getRegionCountOfPlayer(localPlayer);

        if (currentRegionCount >= 2) {
            player.sendMessage(ChatColor.RED + "You already have " + String.valueOf(currentRegionCount) + " with a max of 2");
            return;
        }

        if (region.volume() / 255 > 30 * 30) {
            player.sendMessage(ChatColor.RED + "Area is too big (max = " + String.valueOf(30 * 30) + ") - Please re-select position #2");
            return;
        }

        double test = region.getMaximumPoint().getX() - region.getMinimumPoint().getX();
        player.sendMessage(ChatColor.LIGHT_PURPLE + String.valueOf(test));

        mgr.addRegion(region);
        player.sendMessage(ChatColor.GREEN + "Your area has been claimed, and is now protected from other users!");

        plugin.getPlayerManager().getPlayerEntry(newClaim.getOwnerName()).setPreviousLocation(null);

        return;
    }
}
