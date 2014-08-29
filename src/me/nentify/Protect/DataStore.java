package me.nentify.Protect;

import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;

import java.util.HashMap;

public class DataStore {

    static HashMap<String, PlayerData> playerDataHashMap = new HashMap<String, PlayerData>();

    public WorldGuardPlugin worldGuard = ((WorldGuardPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldGuard"));

    public PlayerData getPlayerData(String playerName) {
        PlayerData playerData = playerDataHashMap.get(playerName);

        if (playerData == null) {
            playerData = new PlayerData(playerName);
        }

        return playerDataHashMap.get(playerName);
    }

    public void addClaim(Claim newClaim) {
        RegionManager regionManager = this.worldGuard.getRegionManager(newClaim.world);

        double x1 = newClaim.loc1.getX();
        double z1 = newClaim.loc1.getZ();
        double x2 = newClaim.loc2.getX();
        double z2 = newClaim.loc2.getZ();

        int worldHeight = Bukkit.getServer().getWorlds().get(0).getMaxHeight() - 1;

        BlockVector min = new Vector(x1, 0.0D, z1).toBlockVector();
        BlockVector max = new Vector(x2, worldHeight, z2).toBlockVector();

        String xAsStr = Double.toString(x1);
        String zAsStr = Double.toString(z1);

        String id = newClaim.ownerName + "-" + xAsStr.substring(0, xAsStr.indexOf(".")) + "x" + zAsStr.substring(0, zAsStr.indexOf("."));

        ProtectedRegion region = new ProtectedCuboidRegion(id, min, max);
        region.getOwners().addPlayer(newClaim.ownerName);
        regionManager.addRegion(region);
    }


}
