package dev.JackaBoi.ReachBlock.Data;

import dev.JackaBoi.ReachBlock.Utils.MathUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

public class PlayerData {

    public static Map<UUID, PlayerData> dataPlayers = Collections.synchronizedMap(new HashMap<>());

    public UUID uuid;
    private Player player;

    public PlayerData(UUID uuid){
        this.uuid = uuid;
    }

    public static PlayerData getData(UUID uuid){
        return dataPlayers.computeIfAbsent(uuid, key -> {
            PlayerData data = new PlayerData(uuid);
            dataPlayers.put(key, data);
            return data;
        });
    }

    public Player getPlayer(){
        if(player == null) this.player = Bukkit.getPlayer(uuid);
        if(player == null) dataPlayers.remove(uuid);
        return player;
    }

    public Long ping, lastServerKP;

    private final Map<Long, Location> preLocations = new LinkedHashMap<>();

    public Location getEstLocation(long time){
        return preLocations.get(Long.parseLong(String.valueOf(MathUtils.getClosetValue(preLocations, time)).replace("Optional[", "").replace("]", "")));
    }

    public void addLocation(long time, Location loc){
        if(preLocations.size() >= 20) preLocations.remove(preLocations.keySet().toArray()[0]);
        preLocations.put(time, loc);
    }

}
