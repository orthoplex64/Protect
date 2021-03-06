package me.nentify.Protect.listeners;

import me.nentify.Protect.Protect;
import me.nentify.Protect.entries.ClaimEntry;
import me.nentify.Protect.entries.PlayerEntry;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import static org.bukkit.Material.*;

public class PlayerListener implements Listener {
    
    private final Protect plugin;
    
    public PlayerListener() {
        plugin = Protect.getInstance();
    }
    
    @EventHandler
    public void onItemHeldEvent(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        PlayerInventory inventory = player.getInventory();
        int currentSlot = event.getNewSlot();
        ItemStack currentItemHeld = inventory.getItem(currentSlot);
        
        if (currentItemHeld.getType() == FEATHER) {
            player.sendMessage(ChatColor.GREEN + "Right click the first corner of your area you wish to protect");
        }
    }
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        ItemStack heldItem = player.getItemInHand();
        
        if (heldItem.getType() == FEATHER) {
            PlayerEntry playerEntry = plugin.getPlayerManager().getPlayerEntry(playerName);
            Location previousLocation = playerEntry.getPreviousLocation();
            Location currentLocation = event.getClickedBlock().getLocation();
            
            if (previousLocation == null) {
                playerEntry.setPreviousLocation(currentLocation);
                player.sendMessage(ChatColor.GREEN + "Corner 1 set, right click the opposite corner of the area you wish to protect");
            } else {
                try {
                    plugin.getClaimManager().addClaim(new ClaimEntry(playerName, previousLocation, currentLocation));
                    player.sendMessage(ChatColor.GREEN + "Your area has been claimed, and is now protected from other users!");
                } catch (Throwable thr) {
                    player.sendMessage(ChatColor.RED + "Claim creation failed");
                }
            }
        }
    }
}
