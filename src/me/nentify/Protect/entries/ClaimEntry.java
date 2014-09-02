package me.nentify.Protect.entries;

import org.bukkit.Location;

public class ClaimEntry {
    
    private String ownerName;
    
    private Location min;
    private Location max;
    
    public ClaimEntry(String ownerName, Location min, Location max) {
        this.ownerName = ownerName;
        this.min = min;
        this.max = max;
    }
    
    public String getOwnerName() {
        return ownerName;
    }
    
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    
    public Location getMin() {
        return min;
    }
    
    public void setMin(Location min) {
        this.min = min;
    }
    
    public Location getMax() {
        return max;
    }
    
    public void setMax(Location max) {
        this.max = max;
    }
}
