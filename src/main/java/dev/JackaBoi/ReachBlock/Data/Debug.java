package dev.JackaBoi.ReachBlock.Data;

import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Debug {

    public List<UUID> debuggers = new ArrayList<>();

    public void debug(String information){
        debuggers.forEach(uuid -> {
            Bukkit.getPlayer(uuid).sendMessage("§8[§4DEBUG§8] §7>> " + information);
        });
    }

}
