package me.nentify.Protect;

import org.bukkit.Location;
import org.bukkit.World;

public class Claim {
    World world;
    Location loc1;
    Location loc2;
    String ownerName;

    public Claim(World world, Location loc1, Location loc2, String ownerName) {
        this.world = world;
        this.loc1 = loc1;
        this.loc2 = loc2;
        this.ownerName = ownerName;
    }
}
