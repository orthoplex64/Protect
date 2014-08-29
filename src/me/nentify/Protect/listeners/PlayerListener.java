package me.nentify.Protect.listeners;

import me.nentify.Protect.Protect;
import me.nentify.Protect.entries.PlayerEntry;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Material.*;

public class PlayerListener implements Listener {

    private final Protect plugin;

    public PlayerListener() {
        plugin = Protect.getInstance();
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        World world = player.getWorld();
        ItemStack heldItem = player.getItemInHand();

        if (heldItem.getType() == FEATHER) {
            PlayerEntry playerEntry = plugin.playerManager().getPlayerEntry(playerName);
            Location previousLocation = playerEntry.getPreviousLocation();

            if (previousLocation == null) {
                playerEntry.setPreviousLocation(event.getClickedBlock().getLocation());
            } else {
                Location currentLocation = event.getClickedBlock().getLocation();
                plugin.claimManager().addClaim(world, previousLocation, currentLocation, playerName);
            }
        }
    }
}
