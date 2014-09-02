package me.nentify.Protect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permissible;

public class Helper {
    
    public static List<String> fromArray(String... values) {
        List<String> results = new ArrayList<String>();
        Collections.addAll(results, values);
        results.remove("");
        
        return results;
    }
    
    public static String[] toArray(List<String> list) {
        return list.toArray(new String[0]);
    }
    
    public static String[] removeFirst(String[] args) {
        List<String> out = fromArray(args);
        
        if (!out.isEmpty()) {
            out.remove(0);
        }
        
        return toArray(out);
    }
    
    public static boolean checkPerm(Permissible toCheck, String perm) {
        if (toCheck.hasPermission(perm)) {
            return false;
        }
        if (toCheck instanceof Player) {
            ((Player) toCheck).sendMessage("You lack the permission: " + perm);
        } else if (toCheck instanceof CommandSender) {
            ((CommandSender) toCheck).sendMessage("You lack the permission: " + perm);
        }
        return false;
    }
}
