package me.nentify.Protect.managers;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.nentify.Protect.Helper;
import static me.nentify.Protect.Helper.checkPerm;
import me.nentify.Protect.Protect;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements CommandExecutor {
    
    private Protect plugin;
    
    public CommandManager() {
        plugin = Protect.getInstance();
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = sender instanceof Player ? (Player) sender : null;
        World world = player == null ? null : player.getWorld();
        
        if (command.getName().equalsIgnoreCase("claim")) {
            if (args.length > 0) {
                String cmd = args[0];
                args = Helper.removeFirst(args);
                
                if (cmd.equalsIgnoreCase("remove")) {
                    if (checkPerm(sender, "protect.remove"))
                        return true;
                    if (player == null) {
                        sender.sendMessage("You must be a player to use that command");
                        return true;
                    }
                    
                    WorldGuardPlugin worldGuard = plugin.getWorldGuard();
                    ApplicableRegionSet set = worldGuard.getRegionContainer().createQuery().getApplicableRegions(player.getLocation());
                    
                    List<ProtectedRegion> ownedRegions = new ArrayList<ProtectedRegion>();
                    for (ProtectedRegion pr : set) {
                        // todo: check if pr represents a Protect claim
                        if (pr.getOwners().getPlayers().contains(player.getName().toLowerCase())) {
                            ownedRegions.add(pr);
                        } else {
                            player.sendMessage(ChatColor.RED + "You do not have permission to delete this claim");
                            return true;
                        }
                    }
                    
                    if (ownedRegions.size() == 1) {
                        ProtectedRegion ownedRegion = ownedRegions.get(0);
                        worldGuard.getRegionManager(world).removeRegion(ownedRegion.getId());
                        player.sendMessage(ChatColor.GREEN + "Your claim " + ownedRegion.getId() + " has been removed");
                    } else if (ownedRegions.size() == 0) {
                        player.sendMessage(ChatColor.RED + "There are no claims here");
                    } else {
                        player.sendMessage(ChatColor.RED + "There are too many claims to DEAL WITH ATM!!!!");
                    }
                    
                    return true;
                }
            }
            
            player.sendMessage(ChatColor.GREEN + "Protection by Nentify");
            return true;
        }
        
        return false;
    }
}
