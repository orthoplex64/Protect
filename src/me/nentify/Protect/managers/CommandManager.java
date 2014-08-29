package me.nentify.Protect.managers;

import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.nentify.Protect.Helper;
import me.nentify.Protect.Protect;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CommandManager implements CommandExecutor {

    private Protect plugin;

    public CommandManager() {
        plugin = Protect.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("claim")) {
            Player player = null;

            if (sender instanceof Player) {
                player = (Player) sender;
            }

            World world = player.getWorld();

            if (args.length > 0) {
                String cmd = args[0];
                args = Helper.removeFirst(args);

                if (cmd.equalsIgnoreCase("remove") && (player.hasPermission("protect.remove") || player.isOp())) {
                    ApplicableRegionSet set = plugin.getWorldGuard().getRegionContainer().createQuery().getApplicableRegions(player.getLocation());
                    player.sendMessage("hi");

                    List<ProtectedRegion> ownedRegions = new ArrayList<ProtectedRegion>();
                    for (Iterator<ProtectedRegion> it = set.iterator(); it.hasNext();) {
                        ProtectedRegion pr = it.next();
                        if (pr.getOwners().getPlayers().contains(player.getName().toLowerCase())) {
                            player.sendMessage(player.getName().toLowerCase());
                            ownedRegions.add(pr);
                        } else {
                            player.sendMessage(ChatColor.RED + "You do not have permission to delete this claim");
                        }
                    }

                    if (ownedRegions.size() == 1) {
                        ProtectedRegion ownedRegion = ownedRegions.get(0);
                        plugin.getWorldGuard().getRegionManager(world).removeRegion(ownedRegion.getId());
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
