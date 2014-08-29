package me.nentify.Protect.managers;

import me.nentify.Protect.Protect;
import me.nentify.Protect.entries.PlayerEntry;

import java.util.HashMap;

public class PlayerManager {

    private Protect plugin;
    private HashMap<String, PlayerEntry> players = new HashMap<String, PlayerEntry>();

    public PlayerManager() {
        plugin = Protect.getInstance();
    }

    public PlayerEntry getPlayerEntry(String playerName) {
        PlayerEntry data = players.get(playerName.toLowerCase());

        if (data == null) {
            data = new PlayerEntry();
            data.setName(playerName);
            players.put(playerName.toLowerCase(), data);
        }

        return data;
    }
}
