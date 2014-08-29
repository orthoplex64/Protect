package me.nentify.Protect;

import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;

public class BlockEventHandler implements Listener {

    public Plugin Protect;
    public WorldGuardPlugin worldGuard = ((WorldGuardPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldGuard"));

    public BlockEventHandler(Protect plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    /*@EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        World world = player.getWorld();
        Block block = e.getBlock();
        RegionManager regionManager = this.worldGuard.getRegionManager(world);
        //Claim claim = new MAKE_A_CLAIM;

        if (block.getType() == Material.OBSIDIAN) {
            //if (player.claimMode == 0) {
                
            //}
        }
    }
/*
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        World world = player.getWorld();
        Block block = e.getBlock();
        RegionManager regionManager = this.worldGuard.getRegionManager(world);

        if (block.getType() == Material.BEDROCK) {
            player.sendMessage("Well done!");
            double x = block.getLocation().getX();
            double z = block.getLocation().getZ();
            int worldHeight = world.getMaxHeight() - 1;

            BlockVector min = new Vector(x - 1, 0.0D, z - 1).toBlockVector();
            BlockVector max = new Vector(x + 1, worldHeight, z + 1).toBlockVector();

            String xAsStr = Double.toString(x);
            String zAsStr = Double.toString(z);

            String id = xAsStr.substring(0, xAsStr.indexOf(".")) + "x" + zAsStr.substring(0, zAsStr.indexOf("."));

            ProtectedRegion region = new ProtectedCuboidRegion(id, min, max);
            region.getOwners().addPlayer(player.getName());
            regionManager.addRegion(region);
        }
    }*/
}
