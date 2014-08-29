package me.nentify.Protect;

import org.bukkit.Location;

import java.util.HashMap;

public class PlayerData {
    String playerName;
    Location previousLocation = null;

    public PlayerData(String playerName) {
        this.playerName = playerName;
    }
}
