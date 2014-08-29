package me.nentify.Protect;

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
        World world = player.getWorld();
        ItemStack heldItem = player.getItemInHand();

        if (heldItem.getType() == FEATHER) {
            PlayerEntry playerEntry = plugin.playerManager().getPlayerEntry(player.getName());
        }
    }
}
