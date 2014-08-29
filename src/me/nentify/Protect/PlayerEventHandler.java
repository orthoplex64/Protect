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
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Material.*;

public class PlayerEventHandler implements Listener {

    public WorldGuardPlugin worldGuard = ((WorldGuardPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldGuard"));

    public PlayerEventHandler(Protect plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        ItemStack heldItem = player.getItemInHand();

        if (heldItem.getType() == FEATHER) {
            player.sendMessage(player.getName());
            PlayerData playerData = Protect.instance.dataStore.getPlayerData(player.getName());
            player.sendMessage(Protect.instance.dataStore.getPlayerData(player.getName()).playerName);

            if (playerData.previousLocation == null) {
                player.sendMessage("pos1preset");
                playerData.previousLocation = event.getClickedBlock().getLocation();
                player.sendMessage("pos1set");
            } else {
                Claim newClaim = new Claim(event.getClickedBlock().getWorld(), playerData.previousLocation, event.getClickedBlock().getLocation(), playerData.playerName);
                Protect.instance.dataStore.addClaim(newClaim);
            }
        }
    }
/*
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
}
