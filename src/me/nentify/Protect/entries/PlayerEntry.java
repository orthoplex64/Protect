package me.nentify.Protect.entries;

import org.bukkit.Location;

import java.util.HashMap;

public class PlayerEntry {
    private String name;
    private Location previousLocation = null;

    public PlayerEntry() {
        //Empty
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getPreviousLocation() {
      return previousLocation;
    }

    public void setPreviousLocation(Location previousLocation) {
        this.previousLocation = previousLocation;
    }
}
